package admin;

import user.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import study.*;
import study.Module;
import tools.Query;

/**
 * 
 * This class contains all the methods and attributs which are used in the Form
 * of a student.
 * 
 * @author Sébastien HERT
 * @author Dejan PARIS
 * 
 */

public class EduForm {

    private Student student;

    private String login;

    private ArrayList<Absence> absences;
    private ArrayList<Module> modules;
    private Map<String, Double> markModules;
    private ArrayList<Exam> exams;
    private Map<String, Double> markExams;

    /**
     * Constructor
     */
    public EduForm() {
    }

    /**
     * Constructor
     * @param login login of the student
     */
    public EduForm(String login) {
        this.login = login;
        this.createAbsences();

        this.createModuleList();
        this.createMarkModules(); 

        this.createExams();
        
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * @param student student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return an list of the absences of the student
     */
    public ArrayList<Absence> getAbsences(){
        return this.absences;
    }

    /**
     * @param absences list of absences to set
     */
    public void setAbsences(ArrayList<Absence> absences){
        this.absences = absences;
    }

    /**
     * @return login of the student
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param login login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return a list of the exams
     */
    public ArrayList<Exam> getExams() {
		return this.exams;
	}

    /**
     * @param exams exams to set
     */
	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
    }
    
    /**
     * @return the exams and the marks
     */
    public Map<String, Double> getMarkExams(){
        return this.markExams;
    }

    /**
     * @param map exams and marks to set
     */
    public void setMarkExams(Map<String, Double>  map){
        this.markExams = map;
    }

    /**
     * @return a list of modules
     */
    public ArrayList<Module> getModules() {
        return this.modules;
    }

    /**
     * @param modules list of modules to set
     */
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * Creates a list of absences from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.absence
     */
    public void createAbsences() {
        this.absences = new ArrayList<>();
        ArrayList<ArrayList<?>> array = new ArrayList<>();
        array = Query.absence(this.login);

        if (!array.isEmpty()) {
            for (int i = 0; i < array.get(0).size(); i++) {
                absences.add(new Absence((Date) array.get(0).get(i), (Time) array.get(1).get(i),
                        (Date) array.get(2).get(i), (Time) array.get(3).get(i), (Boolean) array.get(4).get(i)));
                        System.out.println(i);
            }
        }
    }

    /**
     * Creates a map with the modules and the marks from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.studentAverage
     */
    public void createMarkModules(){
        markModules = new HashMap<>();
        for (int i =0; i<modules.size(); i++){
            String moduleName = modules.get(i).getName();
            double average = Query.studentAverage(moduleName, this.login);
            markModules.put(moduleName, average);
        }
    }

    /**
     * Creates a list of modules from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.courses
     */
    public void createModuleList(){
        modules = new ArrayList<>();
        ArrayList<String> modulesNames = new ArrayList<>();

        modulesNames = Query.courses(this.login);


        for (int i = 0 ; i < modulesNames.size(); i++){
            modules.add(new Module((String) modulesNames.get(i)));
        }
    }

    /**
     * Creates a map with the exams and the marks from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.exams
     */
    public void createExams(){
        markExams = new HashMap<>();
        exams = new ArrayList<>();
        for( int i = 0; i< modules.size(); i++ ){
            ArrayList<ArrayList<?>> array = new ArrayList<>();
            array = Query.exams(this.modules.get(i).getName(), this.login);

            if (! array.isEmpty()){
                for ( int j = 0; j< array.get(0).size(); j++ ){
                    // System.out.println(array.get(3).get(j));
                    Exam exam = new Exam(
                        (String) array.get(0).get(j),
                        (Date) array.get(3).get(j),
                        (Integer) array.get(2).get(j),
                        modules.get(i)
                    );
                    exams.add(exam);
                    markExams.put(exam.getName(), Double.valueOf((Integer)array.get(1).get(j)));
                }
            }
        }
    }

    /**
     * Lists all of the TUs containing the modules of 'modules', and their averages marks.
     * @return List of a list of TUs and a list of the respective average marks.
     * @author Dejan PARIS
     */
    public ArrayList<ArrayList<?>> listOfTUs()
    {
        int coeff = 0; double prev;
        ArrayList<ArrayList<?>> result = new ArrayList<>();
        ArrayList<TeachingUnit> units = new ArrayList<>();
        ArrayList<Double> averages = new ArrayList<>();
        for (int i=0 ; i<modules.size() ; i++)
        {
            if (!units.contains(modules.get(i).getUnit()))
            {
                units.add(modules.get(i).getUnit());
                double zero = 0;
                averages.add(zero);
            }
        }
        for (int i=0 ; i<units.size() ; i++)
        {
            for (int j=0 ; j<modules.size() ; j++)
            {
                coeff = 0;
                if (units.get(i).equals(modules.get(j).getUnit()))
                {
                    prev = averages.get(i);
                    averages.set(i, prev + markModules.get(modules.get(j).getName()));
                    coeff += modules.get(j).getCoeff();
                }
            }
            prev = averages.get(i); averages.set(i, prev / coeff);
        }
        result.add(units);
        result.add(averages);
        return result;
    }

    /**
     * Generates a HTML table from a student's registered marks.
     * @return HTML code used to create the table.
     * @author Dejan PARIS
     */
    public String createReportTable()
    {
        int even;
        int mod = 1;
        String code = "";
        ArrayList<ArrayList<?>> units = listOfTUs();

        for (int i=0 ; i<units.get(0).size() ; i++)
        {
            if (i>0)
                code += "<tr>\n<td id=\"blank\"></td>\n<td id=\"blank\"></td>\n</tr>\n";
            code += "<tr>\n<th> "+((TeachingUnit) (units.get(0).get(i))).getName()+" </th>\n<th> "+Double.toString((double) units.get(1).get(i)) + " </th>\n</tr>\n";
            
            even = -1;
            for (int j=0 ; j<modules.size() ; j++)
            {
                if (units.get(0).get(i).equals(modules.get(j).getUnit()))
                {
                    code += "<tr>\n<td id=\"mod"+Integer.toString(mod)+"\"> "+modules.get(j).getName()+" </td>\n<td id=\"mod"+Integer.toString(mod)+"\"> "+Double.toString(markModules.get(modules.get(j).getName()))+" </td>\n</tr>\n";
                    even *= -1;
                    mod += even;
                }
            }
        }

        return code;
    }

    /**
     * Creates a report of a student's registered marks. Can be detailed (all marks) or not (only average marks).
     * @param student Student whose report will be created.
     * @param option The report will be short if 0, detailed if not.
     * @author Dejan PARIS
     */
    public void generateReport(String firstname, String surname, Course course, int option) throws IOException
    {
        File tmpDir = new File("./reports");
        File tmpFile;
        if (option == 0)
            tmpFile = new File("./reports/template.html");
        else
            tmpFile = new File("./reports/template_detail.html");

        if (!tmpDir.exists())
            tmpDir.mkdirs();
        if (!tmpFile.exists())
        {
            Files.createFile(Path.of(tmpFile.getPath()));
            if (!tmpFile.exists())
            {
                throw new IOException("Error creating file");
            }
            FileWriter temp = new FileWriter(tmpFile, false);
            if (option == 0)
                temp.write("<!DOCTYPE html>\n<html style=\"font-family:helvetica\">\n<meta charset=\"UTF-8\">\n<body>\n\n<div class=\"container\"><img src=\"logo.png\" alt=\"ERiP\" style=\"width:96px; height:96px; position:absolute; left:20px\"><p align=\"right\">Année universitaire $y1/$y2</p></div>\n<h1 align=\"center\"><span style=\"border:2px solid black\">&nbspRELEVE DE NOTES&nbsp</span><br><small>$year année</small></h1>\n<table style=\"width:100%\">\n  <tr></tr>\n</table> \n<p><b>&nbsp &nbsp Nom :</b> $lastname &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>Prénom :</b> $firstname &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>Filière :</b> $course</p>\n<table style=\"width:100%\">\n  <tr></tr>\n</table> \n<p align=\"center\" style=\"background-color:gainsboro; padding:2px\"><b><big>Résultats</big></b></p>\n<style>\n      table, th {\n          border-collapse: collapse;\n      }\n      th {\n        padding: 4px;\n          border: 2px solid black;\n      }\n      #mod1 {\n        background-color: #f0f0f0;\n        padding: 4px;\n        text-align: center;\n    border: 1px solid black;\n      }\n      #mod2 {\n        padding: 4px;\n        text-align: center;\n    border: 1px solid black;\n      }\n      #blank {\n        padding: 12px;\n    border: 0px;\n      }\n</style>\n\n<table style=\"width:100%\" border=\"4\">\n$table\n</table> \n<br><p><b>&nbsp &nbsp Absences injustifiées :</b> $absences</p>\n\n</body>\n</meta>\n</html>\n\n");
            else
                temp.write("<!DOCTYPE html>\n<html style=\"font-family:helvetica\">\n<meta charset=\"UTF-8\">\n<body>\n\n<div class=\"container\"><img src=\"logo.png\" alt=\"ERiP\" style=\"width:96px; height:96px; position:absolute; left:20px\"><p align=\"right\">Année universitaire $y1/$y2</p></div>\n<h1 align=\"center\"><span style=\"border:2px solid black\">&nbspRELEVE DE NOTES DETAILLE&nbsp</span><br><small>$year année</small></h1>\n<table style=\"width:100%\">\n  <tr></tr>\n</table> \n<p><b>&nbsp &nbsp Nom :</b> $lastname &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>Prénom :</b> $firstname &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<b>Filière :</b> $course</p>\n<table style=\"width:100%\">\n  <tr></tr>\n</table> \n<p align=\"center\" style=\"background-color:gainsboro; padding:2px\"><b><big>Notes et résultats</big></b></p>\n<style>\n      table, th {\n          border-collapse: collapse;\n      }\n      #mod0name {\n        padding: 4px;\n        min-width: 150px;\n        text-align: center;\n          border: 2px solid black;\n      }\n      #mod0mark {\n        min-width: 100px;\n        padding: 4px;\n        text-align: center;\n        border: 2px solid black;\n      }\n      #mod1name {\n        background-color: #f0f0f0;\n        min-width: 150px;\n        padding: 4px;\n        text-align: center;\n        border: 1px solid black;\n      }\n      #mod1mark {\n        background-color: #f0f0f0;\n        min-width: 100px;\n        padding: 4px;\n        text-align: center;\n        border: 1px solid black;\n      }\n      #mod2name {\n        padding: 4px;\n        min-width: 150px;\n        text-align: center;\n        border: 1px solid black;\n      }\n      #mod2mark {\n        padding: 4px;\n        min-width: 100px;\n        text-align: center;\n        border: 1px solid black;\n      }\n      #blank {\n        padding: 12px;\n        border: 0px;\n        border-bottom: 2px solid black;\n        border-top: 2px solid black;\n      }\n</style>\n\n<table style=\"width:100%\" border=\"4\">\n$table\n<br><p><b>&nbsp &nbsp Absences injustifiées :</b> $absences</p>\n\n</body>\n</meta>\n</html>\n\n");
            temp.close();
        }

        String path = "./reports/"+surname+"_"+firstname+"_"+course.toString();
        if (option != 0)
            path += "_detail";
        path += ".html";

        Files.copy(Path.of(tmpFile.getPath()), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
        if (!(new File(path)).exists())
        {
            throw new IOException("Error creating file");
        }

        int year = course.year();
        int absenceTotal = 0;
        for (int i=0 ; i<this.absences.size() ; i++)
        {
            if (!this.absences.get(i).isJustified())
                absenceTotal++;
        }

        int y1 = Year.now().getValue();
        int y2;
        if (MonthDay.now().getMonthValue() < 9)
            y2 = y1-1;
        else
            y2 = y1+1;

        String table;
        if (option == 0)
            table = createReportTable();
        else
            table = createDetailedReportTable();

        String[] data = {Integer.toString(y1), Integer.toString(y2), Integer.toString(year), surname, firstname, table, Integer.toString(absenceTotal)};
        String[] tags = {"$y1", "$y2", "$year", "$lastname", "$firstname", "$course", "$table", "$absences"};

        String text = Files.readString(Path.of(path));
        for (int i=0 ; i<9 ; i++)
            text = text.replace(tags[i], data[i]);
        FileWriter fw = new FileWriter(path, false);
        fw.write(text);
        fw.close();
    }

    /**
     * Generates a "detailed" HTML table from a student's registered marks.
     * @return HTML code used to create the table.
     * @author Dejan PARIS
     */
    public String createDetailedReportTable()
    {
        int mod = 1;
        int even;
        int cols = findMaxExams();
        int counter = 0;
        String code = "";
        ArrayList<ArrayList<?>> units = listOfTUs();
        ArrayList<Integer> rank = new ArrayList<>();

        code += "<tr>\n<th id=\"mod0name\"> UE / Module </th>\n<th id=\"mod0mark\"> Moyenne </th>\n";
        for (int k=0 ; k<cols ; k++)
        {
            code += "<th id=\"mod0mark\"> Note (Coefficient) </th>\n";
        }
        code += "<th id=\"mod0mark\"> Classement </th>\n</tr>\n";

        for (int i=0 ; i<units.get(0).size() ; i++)
        {
            if (i>0)
            {
                code += "<tr>";
                for (int k=0 ; k<cols+3 ; k++)
                {
                    code += "<td id=\"blank\"></td>\n";
                }
                code += "</tr>\n";
            }

            code += "<tr>\n<th id=\"mod0name\"> "+((TeachingUnit) (units.get(0).get(i))).getName()+" </th>\n<th id=\"mod0mark\"> "+Double.toString((double) units.get(1).get(i)) + " </th>\n";
            for (int k=0 ; k<cols ; k++)
            {
                code += "<th id=\"mod0mark\"></th>";
            }
            rank = unitRank(login, ((TeachingUnit) (units.get(0).get(i))).getName(), (double) units.get(1).get(i));
            code += "<th id=\"mod0mark\">"+Integer.toString(rank.get(0))+"/"+Integer.toString(rank.get(1))+"</th>\n</tr>\n";

            even = -1;
            for (int j=0 ; j<modules.size() ; j++)
            {
                if (units.get(0).get(i).equals(modules.get(j).getUnit()))
                {
                    code += "<tr>\n<td id=\"mod"+Integer.toString(mod)+"name\"> "+modules.get(j).getName()+" </td>\n<td id=\"mod"+Integer.toString(mod)+"mark\"> "+Double.toString(markModules.get(modules.get(j).getName()))+" </td>\n";
                    for (int k=0 ; k<exams.size() ; k++)
                    {
                        if (modules.get(j).equals(exams.get(k).getModule()))
                        {
                            code += "<td id=\"mod"+Integer.toString(mod)+"mark\"> "+Double.toString(markExams.get(exams.get(k).getName()))+" ("+Integer.toString(exams.get(k).getCoeff())+") </td>\n";
                            counter++;
                        }
                    }
                    for (int k=0 ; k<cols-counter ; k++)
                    {
                        code += "<td id=\"mod"+Integer.toString(mod)+"mark\"></td>\n";
                    }
                    rank = moduleRank(login, modules.get(j).getName());
                    code += "<th id=\"mod"+Integer.toString(mod)+"mark\">"+Integer.toString(rank.get(0))+"/"+Integer.toString(rank.get(1))+"</th>\n</tr>\n";
                    counter = 0;
                    even *= -1;
                    mod += even;
                }
            }
        }

        return code;
    }

    /**
     * Finds the maximum number of exams for any of the modules that the student attends.
     * @return Maximum number of exams for a single module.
     * @author Dejan PARIS
     */
    public int findMaxExams()
    {
        int max = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0 ; i<modules.size() ; i++)
        {
            list.add(0);
        }
        for (int i=0 ; i<exams.size() ; i++)
        {
            int index = modules.indexOf(exams.get(i).getModule());
            int prev = list.get(index);
            list.set(index, prev+1);
        }
        for (int i=0 ; i<modules.size() ; i++)
        {
            if (max < list.get(i)) max = list.get(i);
        }
        return max;
    }

    /**
     * Gives the rank of a given student in a module.
     * @param login Login of the student.
     * @param modName Module's name.
     * @return Rank and total number of students attending the module.
     * @author Dejan PARIS
     * @see Query.moduleStudentsAverage
     */
    public ArrayList<Integer> moduleRank(String login, String modName)
    {
        int counter = 1;
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<ArrayList<?>> average = new ArrayList<>();
        average = Query.moduleStudentsAverage(modName);
        double mark = markModules.get(modName);
        rank.set(1, average.size());
        for (int i=0 ; i<average.size() ; i++)
        {
            if ((double) average.get(i).get(2) > mark)
                counter++;
        }
        rank.set(0, counter);
        return rank;
    }

    /**
     * Gives the rank of a given student in a TU.
     * @param login Login of the student.
     * @param unit TU's name.
     * @return Rank and total number of students registered in the TU.
     * @author Dejan PARIS
     * @see Query.modulesInTU
     * @see Query.unitAttendees
     * @see Query.studentAverage
     * @see Query.coeffInTU
     */
    public ArrayList<Integer> unitRank(String login, String unit, double mark)
    {
        int counter = 1;
        double avg; int coeff;
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<ArrayList<Object>> average = new ArrayList<>();
        ArrayList<String> modules = Query.modulesInTU(unit);
        average.set(0, Query.unitAttendees(unit));
        for (int k=0 ; k<average.get(0).size() ; k++)
        {
            avg = 0; coeff = 0;
            for (int i=0 ; i<modules.size() ; i++)
            {
                avg += Query.studentAverage(modules.get(i), (String) average.get(0).get(k));
                coeff += (int) Query.coeffInTU(modules.get(i)).get(1);
            }
            average.get(1).set(k, avg/coeff);
        }
        rank.set(1, average.size());
        for (int i=0 ; i<average.get(1).size() ; i++)
        {
            if ((double) average.get(1).get(i) > mark)
                counter++;
        }
        rank.set(0, counter);
        return rank;
    }
}

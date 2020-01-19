package admin;

import user.*;

import java.io.File;
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

    public EduForm() {
    }

    public EduForm(String login) {
        this.login = login;
        this.createAbsences();

        this.createModuleList();
        this.createMarkModules(); 

        this.createExams();
        
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Absence> getAbsences(){
        return this.absences;
    }

    public void setAbsences(ArrayList<Absence> absences){
        this.absences = absences;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Exam> getExams() {
		return this.exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
    }
    
    public Map<String, Double> getMarkExams(){
        return this.markExams;
    }

    public void setMarkExams(Map<String, Double>  map){
        this.markExams = map;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }


    public void createAbsences() {
        ArrayList<ArrayList<?>> array = new ArrayList<>();
        array = Query.absence(this.login);

        if (!array.isEmpty()) {
            for (int i = 0; i < array.get(0).size(); i++) {
                absences.add(new Absence((Date) array.get(0).get(i), (Time) array.get(1).get(i),
                        (Date) array.get(2).get(i), (Time) array.get(3).get(i), (Boolean) array.get(4).get(i)));
            }
        }
    }

    public void createMarkModules(){
        markModules = new HashMap<>();
        for (int i =0; i<modules.size(); i++){
            String moduleName = modules.get(i).getName();
            double average = Query.studentAverage(moduleName, this.login);
            markModules.put(moduleName, average);
        }
    }

    public void createModuleList(){
        ArrayList<String> modulesNames = new ArrayList<>();

        modulesNames = Query.courses(this.login);


        for (int i = 0 ; i < modulesNames.size(); i++){
            modules.add(new Module(modulesNames.get(i).toString()));
        }
    }

    public void createExams(){
        markExams = new HashMap<>();
        for( int i = 0; i< modules.size(); i++ ){
            ArrayList<ArrayList<?>> array = new ArrayList<>();
            array = Query.exams(this.modules.get(i).getName(), this.login);

            if (! array.isEmpty()){
                for ( int j = 0; j< array.get(0).size(); j++ ){
                    Exam exam = new Exam(
                        (String) array.get(0).get(j),
                        (Date) array.get(3).get(j),
                        (Integer) array.get(2).get(j),
                        modules.get(i)
                    );
                    exams.add(exam);
                    markExams.put(exam.getName(), (Double) array.get(1).get(j));
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
     * Creates a report of a student's registered marks. Can be detailed (all marks) or not (only averages).
     * @param student Student whose report will be created.
     * @param option The report will be short if 0, detailed if not.
     * @author Dejan PARIS
     */
    public void generateReport(Person student, int option) throws IOException
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
            // appeler une erreur ou recréer le template
        }

        String surname = student.getSurname();
        String firstname = student.getFirstname();
        Course course = ((Student) student.getRole()).getCourse();

        String path = "./reports/"+surname+"_"+firstname+"_"+course.toString();
        if (option != 0)
            path += "_detail";
        path += ".html";

        Files.copy(Path.of(tmpFile.getPath()), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
        if (!(new File(path)).exists())
        {
            // appeler une erreur ou recommencer
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
        Files.writeString(Path.of(path), text);
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

        code += "<tr>\n<th id=\"mod0name\"> UE / Module </th>\n<th id=\"mod0mark\"> Moyenne </th>\n";
        for (int k=0 ; k<cols ; k++)
        {
            code += "<th id=\"mod0mark\"> Note (Coefficient) </th>\n";
        }
        code += "</tr>\n";

        for (int i=0 ; i<units.get(0).size() ; i++)
        {
            if (i>0)
            {
                code += "<tr>";
                for (int k=0 ; k<cols+2 ; k++)
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
            code += "</tr>\n";

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
                    code += "</tr>\n";
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
}

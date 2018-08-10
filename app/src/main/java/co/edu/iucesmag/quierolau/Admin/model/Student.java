package co.edu.iucesmag.quierolau.Admin.model;

public class Student {
    private String identification;
    private String name;
    private String attempts;
    private String program;

    public Student(String identification, String name, String attempts, String program) {
        this.identification = identification;
        this.name = name;
        this.attempts = attempts;
        this.program = program;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}

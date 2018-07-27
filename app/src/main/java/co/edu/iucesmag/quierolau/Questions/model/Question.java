package co.edu.iucesmag.quierolau.Questions.model;

public class Question {
    private Integer id;
    private String title;
    private String opt_a;
    private String opt_b;
    private String opt_c;
    private String opt_d;
    private String res;

    public Question(Integer id, String title, String opt_a, String opt_b, String opt_c, String opt_d, String res) {
        this.id = id;
        this.title = title;
        this.opt_a = opt_a;
        this.opt_b = opt_b;
        this.opt_c = opt_c;
        this.opt_d = opt_d;
        this.res = res;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpt_a() {
        return opt_a;
    }

    public void setOpt_a(String opt_a) {
        this.opt_a = opt_a;
    }

    public String getOpt_b() {
        return opt_b;
    }

    public void setOpt_b(String opt_b) {
        this.opt_b = opt_b;
    }

    public String getOpt_c() {
        return opt_c;
    }

    public void setOpt_c(String opt_c) {
        this.opt_c = opt_c;
    }

    public String getOpt_d() {
        return opt_d;
    }

    public void setOpt_d(String opt_d) {
        this.opt_d = opt_d;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}

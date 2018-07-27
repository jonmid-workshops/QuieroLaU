package co.edu.iucesmag.quierolau.Questions.model;

public class ResponseQuestion {
    private boolean success;
    private String message;
    private String attempts;

    public ResponseQuestion(boolean success, String message, String attempts) {
        this.success = success;
        this.message = message;
        this.attempts = attempts;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }
}

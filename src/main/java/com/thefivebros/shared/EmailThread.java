package com.thefivebros.shared;

public class EmailThread extends Thread{
    private String toEmailAddress;
    private String subject;
    private String bodyHTML;
    private String errorMessage;

    public EmailThread(String toEmailAddress, String subject, String bodyHTML) {
        this.toEmailAddress = toEmailAddress;
        this.subject = subject;
        this.bodyHTML = bodyHTML;
    }

    @Override
    public void run() {
        errorMessage = AzureEmail.sendEmail(toEmailAddress, subject, bodyHTML);
        // TODO: Implement a backup email service in case Azure is down
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

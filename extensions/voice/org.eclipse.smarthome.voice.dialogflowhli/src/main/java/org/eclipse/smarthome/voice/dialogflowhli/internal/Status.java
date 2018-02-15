
package org.eclipse.smarthome.voice.dialogflowhli.internal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("errorType")
    @Expose
    private String errorType;
    @SerializedName("webhookTimedOut")
    @Expose
    private boolean webhookTimedOut;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public boolean isWebhookTimedOut() {
        return webhookTimedOut;
    }

    public void setWebhookTimedOut(boolean webhookTimedOut) {
        this.webhookTimedOut = webhookTimedOut;
    }

}

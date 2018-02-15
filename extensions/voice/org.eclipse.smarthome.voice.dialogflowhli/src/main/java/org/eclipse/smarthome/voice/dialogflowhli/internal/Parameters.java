
package org.eclipse.smarthome.voice.dialogflowhli.internal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {

    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("state")
    @Expose
    private String state;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}

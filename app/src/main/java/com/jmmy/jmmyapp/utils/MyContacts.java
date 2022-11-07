package com.jmmy.jmmyapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class MyContacts implements Parcelable {
    public String name;
    public String phone;
    public String note;

    public MyContacts (){
    }

    protected MyContacts(Parcel in) {
        name = in.readString();
        phone = in.readString();
        note = in.readString();
    }

    public static final Creator<MyContacts> CREATOR = new Creator<MyContacts>() {
        @Override
        public MyContacts createFromParcel(Parcel in) {
            return new MyContacts(in);
        }

        @Override
        public MyContacts[] newArray(int size) {
            return new MyContacts[size];
        }
    };

    @Override
    public String toString() {
        return "MyContacts{" +
            "name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", note='" + note + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(note);
    }
}

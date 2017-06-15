package com.example.hzh.servicedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lanxumit on 2017/5/3.
 */

public class PersonEntity implements Parcelable {
    private int age;
    private int sex;
    private String name;

    public PersonEntity() {

    }

    protected PersonEntity(Parcel in) {
        age = in.readInt();
        sex = in.readInt();
        name = in.readString();
    }

    public static final Creator<PersonEntity> CREATOR = new Creator<PersonEntity>() {
        @Override
        public PersonEntity createFromParcel(Parcel in) {
            return new PersonEntity(in);
        }

        @Override
        public PersonEntity[] newArray(int size) {
            return new PersonEntity[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeInt(sex);
        dest.writeString(name);
    }
}

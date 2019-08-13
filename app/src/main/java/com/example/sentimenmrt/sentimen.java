package com.example.sentimenmrt;
import com.google.gson.annotations.SerializedName;

public class sentimen {
    @SerializedName("komentar")
    private char Komentar;

    @SerializedName("jumlah")
    private int Jumlah;

    public char getKomentar() {
        return Komentar;
    }
    public int getJumlah() {
        return Jumlah;
    }
}

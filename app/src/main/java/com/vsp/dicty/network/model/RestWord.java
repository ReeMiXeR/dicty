package com.vsp.dicty.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestWord {

    @SerializedName("head")
    @Expose
    private Head head;
    @SerializedName("def")
    @Expose
    private List<Def> def = null;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Def> getDef() {
        return def;
    }

    public void setDef(List<Def> def) {
        this.def = def;
    }

    public static class Head {


    }

    public static class Def {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("ts")
        @Expose
        private String ts;
        @SerializedName("tr")
        @Expose
        private List<Tr> tr = null;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        public List<Tr> getTr() {
            return tr;
        }

        public void setTr(List<Tr> tr) {
            this.tr = tr;
        }

    }

    public static class Tr {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("gen")
        @Expose
        private String gen;
        @SerializedName("syn")
        @Expose
        private List<Syn> syn = null;
        @SerializedName("mean")
        @Expose
        private List<Mean> mean = null;
        @SerializedName("ex")
        @Expose
        private List<Ex> ex = null;
        @SerializedName("asp")
        @Expose
        private String asp;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getGen() {
            return gen;
        }

        public void setGen(String gen) {
            this.gen = gen;
        }

        public List<Syn> getSyn() {
            return syn;
        }

        public void setSyn(List<Syn> syn) {
            this.syn = syn;
        }

        public List<Mean> getMean() {
            return mean;
        }

        public void setMean(List<Mean> mean) {
            this.mean = mean;
        }

        public List<Ex> getEx() {
            return ex;
        }

        public void setEx(List<Ex> ex) {
            this.ex = ex;
        }

        public String getAsp() {
            return asp;
        }

        public void setAsp(String asp) {
            this.asp = asp;
        }

    }

    public static class Syn {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("gen")
        @Expose
        private String gen;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getGen() {
            return gen;
        }

        public void setGen(String gen) {
            this.gen = gen;
        }

    }



    public static class Tr_ {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public static class Mean {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }



    public static class Ex {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("tr")
        @Expose
        private List<Tr_> tr = null;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Tr_> getTr() {
            return tr;
        }

        public void setTr(List<Tr_> tr) {
            this.tr = tr;
        }
    }


}
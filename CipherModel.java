package ca.yorku.eecs.kryptonote;


public class CipherModel {
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRTUVWXYZ";

    public CipherModel(String x) {
        this.key = x;
    }

    private String makePad(String note){
        String pad= this.key;
        for (; pad.length() < note.length(); ){
            pad+= this.key;
        }
        return key;
    }

    public String encrypt(String note){
        String pad=makePad(note);
        String result="";
        for(int i=0;i<note.length();i++){
            String c =note.substring(i,i+1);
            int position=ALPHABET.indexOf(c);
            int shift=Integer.parseInt(pad.substring(i,i+1));
            int newPosition =(position + shift)% ALPHABET.length();
            result+=ALPHABET.substring(newPosition,newPosition+1);
        }
        return result;
    }

    public String decrypt(String note){
        String pad=makePad(note);
        String result="";
        for(int i=0;i<note.length();i++){
            String c =note.substring(i,i+1);
            int position=ALPHABET.indexOf(c);
            int shift=Integer.parseInt(pad.substring(i,i+1));
            int newPosition =(position - shift)% ALPHABET.length();
            result+=ALPHABET.substring(newPosition,newPosition+1);
        }
        return result;
    }


}

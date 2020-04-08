package sample;
public class Javaline {

    private String javaVar="";
    private String commentVar="";
    private int javaLen=0;

    public Javaline(String strLine, int bracketCount){

        if (!strLine.equals("")){
            subSplit(strLine, bracketCount);
        }
    }

    private void subSplit(String strLine,
                          int bracketCount){
        int i;
        String strIndent;
        int x = strLine.lastIndexOf('"');
        i = strLine.indexOf('/');

        if (i < x){
            i = strLine.indexOf('/', x);
        }

        if (i+1 < strLine.length()){

            if (strLine.charAt(i+1) == '/') {

                javaVar = strLine.substring(0, i);

                javaVar = javaVar.trim();


                if (javaVar.indexOf('{') > -1 || optimizer.blnFlag){
                    bracketCount--;

                }

                strIndent = indentJava(bracketCount);
                javaVar = strIndent + javaVar;




                commentVar = strLine.substring(i);
            }
            else {

                javaVar = strLine;
                javaVar = javaVar.trim();
                if ((javaVar.indexOf('{') <= -1) || (javaVar.indexOf('}') <= -1)) {
                    if (javaVar.indexOf('{') > -1 || javaVar.contains("case")){
                        bracketCount--;
                    }
                    else if (javaVar.contains("break")){
                        bracketCount++;
                    }
                }

                strIndent = indentJava(bracketCount);
                javaVar = strIndent + javaVar;

                commentVar = "";
            }
        }
        else {

            javaVar = strLine;
            javaVar = javaVar.trim();
            if ((javaVar.indexOf('{') <= -1) || (javaVar.indexOf('}') <= -1)) {
                if (javaVar.indexOf('{') > -1){

                    bracketCount--;
                }
            }

            strIndent = indentJava(bracketCount);
            javaVar = strIndent + javaVar;

            commentVar = "";
        }

        if (optimizer.blockComment){




            javaVar = "";
            strIndent = indentJava(bracketCount);
            javaVar = strIndent + javaVar;
            strLine = strLine.trim();

            commentVar = strLine;


        }
    }


    public int getJavaLineLength(){
        int length = javaVar.length();
        javaLen = javaVar.length();
        return length;
    }

    public static String countSpaces(int value){
        return " ".repeat(Math.max(0, value));
    }

    private static String indentJava(int bracketCount){
        return "  ".repeat(Math.max(0, bracketCount));
    }

    public String returnLineWithCommentAt(int index){
        String stringOfSpaces;
        int value;
        String strRetLine;


        value = index - javaLen;
        stringOfSpaces = countSpaces(value); //Replace 1
        strRetLine = javaVar + stringOfSpaces + commentVar;

        return strRetLine;
    }

    public String toString(){
        return "JavaLine(String strLine, bracketCount)";
    }
}

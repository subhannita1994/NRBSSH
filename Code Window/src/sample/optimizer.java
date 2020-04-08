package sample;
import java.util.*;

class optimizer {

    private ArrayList<Javaline> program = new ArrayList<>();
    public static Scanner userInput = new Scanner(System.in);


    public static boolean blnFlag=false;
    private boolean blnCheck=false;

    private static boolean commentFlag = false;
    public static boolean blockComment=false;




    public static void main(String[] args){
        ( new optimizer() ).process();
    }


    private void process(){
        readProgram();
        int max = findMaxJavaLineLength();
        printIndentedProgram( max+1 );
    }


    private void readProgram(){



        int bracketCount=0;

        while (userInput.hasNextLine()){
            String strLine = userInput.nextLine();

            if (strLine.equals("") && bracketCount == 0){
                break;
            }
            if (!strLine.equals("")){

                bracketCount = checkFlags(strLine, bracketCount);

                blockComment = checkBlock(strLine);

                bracketCount = checkChars(strLine, bracketCount);
                if (!blnFlag) {
                    bracketCount = checkBrackets(strLine, bracketCount);
                }
                //declaring new instance of JavaLine object parsing input strLine at given time
                program.add( new Javaline( strLine, bracketCount ) );
            }

        }
    }

    private int checkFlags(String strLine, int bracketCount){
        if (blnFlag && strLine.contains("{")) {						//|| strLine.contains("case"))
            // Bracket
            bracketCount--;
            blnFlag=false;
            blnCheck = false;
        }
        else if (blnFlag) {
            blnCheck = true;
            blnFlag = false;
        }
        else {
            if (blnCheck) {
                bracketCount--;
                blnCheck = false;
            }

        }
        if (blockComment && commentFlag){
            blockComment = false;
            commentFlag = false;
        }
        if (blockComment){
            int v = strLine.indexOf('*');
            if (strLine.indexOf('*') > -1){
                if (v+1 < strLine.length()){
                    if (strLine.charAt(v+1) == '/'){

                        blockComment = true;
                        commentFlag = true;
                    }
                }
            }
        }


        return bracketCount;
    }

    private static int checkBrackets(String strLine, int bracketCount){

        boolean speechDetected = false;
        if  (strLine.contains("\"")) {
            speechDetected = true;
            //index of { OR  } > lastIndex of '"'
        }

        if (!speechDetected){

            if (strLine.contains("{")) {
                bracketCount++;
            }

            if (strLine.contains("}")) {
                bracketCount--;
            }

        }
        else {
            if (strLine.lastIndexOf("\"") < strLine.lastIndexOf("{")){

                bracketCount++;



            }
            if (strLine.lastIndexOf("\"") < strLine.lastIndexOf("}")) {


                bracketCount--;

            }
        }
        return bracketCount;
    }

    private int checkChars(String strLine, int bracketCount) {
        String checkChar;

        strLine = strLine.trim();
        if (strLine.length() > 3){
            checkChar = strLine.charAt(0) + "" + strLine.charAt(1) + "" + strLine.charAt(2);

            if (!strLine.contains("{")) {


                if (checkChar.equals("do ") || checkChar.equals("do(") || checkChar.equals("for") || checkChar.equals("whi") || checkChar.equals("if ") || checkChar.equals("els") || checkChar.equals("if(") || checkChar.equals("try") || checkChar.equals("cat") || checkChar.equals("swi") || checkChar.equals("cas")) {
                    bracketCount++;
                    blnFlag = true;
                }
            }
        }
        return bracketCount;
    }

    private static boolean checkBlock(String strLine){
        int i = strLine.indexOf('/');
        boolean temp = blockComment;
        if (i > -1){
            if (i+1 < strLine.length()){
                if (strLine.charAt(i+1) == '*'){

                    temp = true;
                }

            }
        }
        return temp;
    }

    private int findMaxJavaLineLength(){
        int max = 0;
        for ( Javaline strLine: program ){
            int lineLength = strLine.getJavaLineLength();
            if ( lineLength > max ) max = lineLength;
        }
        return max;
    }

    private void printIndentedProgram( int pos ){
        for ( Javaline strLine: program ){
            System.out.println(strLine.returnLineWithCommentAt(pos));
        }
    }
}

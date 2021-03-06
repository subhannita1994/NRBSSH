package sample;
import java.util.*;

class Optimizer {

    public static Scanner userInput = new Scanner(System.in);
    public static boolean blnFlag = false;
    public static boolean blockComment = false;
    private static boolean commentFlag = false;
    private ArrayList<JavaLine> program = new ArrayList<>();
    private boolean blnCheck = false;

    private static int checkBrackets(String strLine, int bracketCount) {

        boolean speechDetected = false;
        if (strLine.indexOf("\"") > -1) {
            speechDetected = true;

        }

        if (speechDetected == false) {

            if (strLine.indexOf("{") > -1) {
                bracketCount++;
            } else {

            }

            if (strLine.indexOf("}") > -1) {
                bracketCount--;
            }

        } else {
            if (strLine.lastIndexOf("\"") < strLine.lastIndexOf("{")) {

                bracketCount++;


            }
            if (strLine.lastIndexOf("\"") < strLine.lastIndexOf("}")) {


                bracketCount--;

            }
        }
        return bracketCount;
    }

    private static boolean checkBlock(String strLine) {
        int i = strLine.indexOf('/');
        boolean temp = blockComment;
        if (i > -1) {
            if (i + 1 < strLine.length()) {
                if (strLine.charAt(i + 1) == '*') {

                    temp = true;
                }

            }
        }
        return temp;
    }

    public void main(String args[]) {
        (new Optimizer) ).process();
    }

    private void process() {
        readProgram();
        int max = findMaxJavaLineLength();
        printIndentedProgram(max + 1);
    }

    private void readProgram() {


        int bracketCount = 0;

        while (userInput.hasNextLine()) {
            String strLine = userInput.nextLine();

            if (strLine.equals("") && bracketCount == 0) {
                break;
            }
            if (!strLine.equals("")) {

                bracketCount = checkFlags(strLine, bracketCount);

                blockComment = checkBlock(strLine);

                bracketCount = checkChars(strLine, bracketCount);
                if (blnFlag == false) {
                    bracketCount = checkBrackets(strLine, bracketCount);
                }

                program.add(new JavaLine(strLine, bracketCount));
            }

        }
    }

    private int checkFlags(String strLine, int bracketCount) {
        if (blnFlag == true && strLine.indexOf("{") > -1) {

            bracketCount--;
            blnFlag = false;
            blnCheck = false;
        } else if (blnFlag == true) {
            blnCheck = true;
            blnFlag = false;
        } else {
            if (blnCheck == true) {
                bracketCount--;
                blnCheck = false;
            } else if (blnCheck == true && !strLine.contains("break;")) {

                blnCheck = true;
            }

        }
        if (blockComment == true && commentFlag == true) {
            blockComment = false;
            commentFlag = false;
        }
        if (blockComment == true) {
            int v = strLine.indexOf('*');
            if (strLine.indexOf('*') > -1) {
                if (v + 1 < strLine.length()) {
                    if (strLine.charAt(v + 1) == '/') {

                        blockComment = true;
                        commentFlag = true;
                    }
                }
            }
        }


        return bracketCount;
    }

    private int checkChars(String strLine, int bracketCount) {
        String checkChar;

        strLine = strLine.trim();
        if (strLine.length() > 3) {
            checkChar = strLine.charAt(0) + "" + strLine.charAt(1) + "" + strLine.charAt(2);

            if (strLine.indexOf("{") == -1) {

                if (checkChar.equals("do ") || checkChar.equals("do(") || checkChar.equals("for") || checkChar.equals("whi") || checkChar.equals("if ") || checkChar.equals("els") || checkChar.equals("if(") || checkChar.equals("try") || checkChar.equals("cat") || checkChar.equals("swi") || checkChar.equals("cas")) {
                    bracketCount++;
                    blnFlag = true;
                }
            }
        }
        return bracketCount;
    }

    private int findMaxJavaLineLength() {
        int max = 0;
        for (JavaLine strLine : program) {
            int lineLength = strLine.getJavaLineLength();
            if (lineLength > max) max = lineLength;
        }
        return max;
    }

    private void printIndentedProgram(int pos) {
        for (JavaLine strLine : program) {
            System.out.println(strLine.returnLineWithCommentAt(pos));
        }
    }
}

package sample;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeTab {
    public static void codeFormat(String filename, int choice) {

        Scanner scanner=new Scanner(System.in);
        int codeMode = choice;
        // you enter the file path you wanted optimized like : c:\main.cpp or we can pre assign it to a destination that our cpp auto save has stored the current file
        String inPath= filename+"\\out.cpp";
        String outPath= filename+"\\temp.cpp";
        File inFile=new File(inPath);
        File outFile=new File(outPath);


        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(inFile));
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outFile));
            if(codeMode==1){
                codeFormat1(bufferedReader, bufferedWriter);
            }else if(codeMode==2){
                codeFormat2(bufferedReader, bufferedWriter);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            System.out.print("Optimized File Saved at  " + outPath);
        }
    }


    public static void addTab(StringBuilder stringBuilder,int tabNum) {
        for(int i=0;i<tabNum;i++)
            stringBuilder.insert(0, "	");
    }

    private static void codeFormat1(BufferedReader bufferedReader,BufferedWriter bufferedWriter) throws IOException {
        String currentCol;
        int tabNum=0;
        StringBuilder stringBuilder=new StringBuilder();
        while((currentCol=bufferedReader.readLine())!=null){
            AtomicInteger curPos= new AtomicInteger();
            boolean begin=false;
            while(curPos.get() <currentCol.length()){
                if(currentCol.charAt(curPos.get())=='#'){
                    curPos.set(getCurPos(bufferedWriter, currentCol, stringBuilder, curPos.get()));
                }else if(currentCol.charAt(curPos.get())=='}'){
                    tabNum--;
                    for(int i=0;i<tabNum;i++)
                        bufferedWriter.write("	");
                    bufferedWriter.write("}");
                    bufferedWriter.newLine();
                }else if(currentCol.charAt(curPos.get())=='{'){
                    addTab(stringBuilder, tabNum);
                    bufferedWriter.write(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    bufferedWriter.newLine();
                    for(int i=0;i<tabNum;i++)
                        bufferedWriter.write("	");
                    bufferedWriter.write("{");
                    bufferedWriter.newLine();
                    tabNum++;
                }else if(currentCol.charAt(curPos.get())==';'){
                    if (stringBuilder.indexOf("for") < 0) {
                        stringBuilder.append(";");
                        addTab(stringBuilder, tabNum);
                        bufferedWriter.write(stringBuilder.toString());
                        bufferedWriter.newLine();
                        stringBuilder.delete(0, stringBuilder.length());
                    } else {
                        stringBuilder.append(";");
                    }
                }else if ((currentCol.charAt(curPos.get()) != ' ' && currentCol.charAt(curPos.get()) != '	') || begin) {
                    begin=true;
                    stringBuilder.append(currentCol.charAt(curPos.get()));
                }

                curPos.getAndIncrement();
            }
        }
    }

    private static int getCurPos(BufferedWriter bufferedWriter, String currentCol, StringBuilder stringBuilder, int curPos) throws IOException {
        while(currentCol.charAt(curPos)!='>'){
            stringBuilder.append(currentCol.charAt(curPos));
            curPos++;
        }
        curPos++;
        bufferedWriter.write(stringBuilder.toString()+">");
        bufferedWriter.newLine();
        stringBuilder.delete(0, stringBuilder.length());
        return curPos;
    }

    private static void codeFormat2(BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        String currentCol;
        int tabNum=0;
        StringBuilder stringBuilder=new StringBuilder();
        while((currentCol=bufferedReader.readLine())!=null){
            int curPos=0;
            boolean begin=false;
            while(curPos<currentCol.length()){
                if(currentCol.charAt(curPos)=='#'){
                    curPos = getCurPos(bufferedWriter, currentCol, stringBuilder, curPos);
                }else if(currentCol.charAt(curPos)=='}'){
                    tabNum--;
                    for(int i=0;i<tabNum;i++)
                        bufferedWriter.write("	");
                    bufferedWriter.write("}");
                    bufferedWriter.newLine();
                }else if(currentCol.charAt(curPos)=='{'){
                    addTab(stringBuilder, tabNum);
                    bufferedWriter.write(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    bufferedWriter.write("{");
                    bufferedWriter.newLine();
                    tabNum++;
                }else if(currentCol.charAt(curPos)==';'){
                    if(stringBuilder.indexOf("for")>=0){
                        stringBuilder.append(";");
                    }else{
                        stringBuilder.append(";");
                        addTab(stringBuilder, tabNum);
                        bufferedWriter.write(stringBuilder.toString());
                        bufferedWriter.newLine();
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                }else if ((currentCol.charAt(curPos) != ' ' && currentCol.charAt(curPos) != '	') || begin) {
                    begin=true;
                    stringBuilder.append(currentCol.charAt(curPos));
                }

                curPos++;
            }
        }
    }
}

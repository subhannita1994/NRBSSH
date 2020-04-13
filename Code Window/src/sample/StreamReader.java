package sample;

import java.io.IOException;
import java.io.InputStream;

 interface CommandListener {

	public void commandOutput(String text);

	public void commandFailed(Exception exp);
}

public class StreamReader extends Thread {

	private InputStream is;
	private CommandListener listener;

	public StreamReader(CommandListener listener, InputStream is) {
		this.is = is;
		this.listener = listener;
		start();
	}

	@Override
	public void run() {
		try {
			int value = -1;
			while ((value = is.read()) != -1) {
				listener.commandOutput(Character.toString((char) value));
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}
}

 interface UserInput {

	public int getUserInputStart();
}

 interface Terminal extends UserInput {
	public void appendTextValue(String text);
}

 class AppendTask implements Runnable {

	private Terminal terminal;
	private String text;

	public AppendTask(Terminal textArea, String text) {
		this.terminal = textArea;
		this.text = text;
	}

	@Override
	public void run() {
		terminal.appendTextValue(text);
	}
}
 class Command {

	private CommandListener listener;
	private Controller contObj;
	private ProcessRunner runner;

	public Command(Controller listener) {
		this.contObj = listener;
	}

	public boolean isRunning() {
		return runner != null && runner.isAlive();
	}

	public void execute(String attachedCode) {
		System.out.println("Options: "+attachedCode);
		runner = new ProcessRunner(contObj, attachedCode);
	}

	public void send(String cmd) throws IOException {
		runner.write(cmd);
	}
}
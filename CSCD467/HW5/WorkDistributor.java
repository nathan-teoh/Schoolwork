import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WorkDistributor {

    public static void main(String[] args) throws Exception {
        System.out.println("The Task server is running.");
        ServerSocket ss = new ServerSocket(9898);
        ThreadPool tp = new ThreadPool();
        ThreadManager tm = new ThreadManager(tp, ss);
        tm.start();
        try {
            while (!tm.isTerminated()) {
                Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String cmd = in.readLine();
                if (!(tp.execute(new Job(s, cmd, tm)))) {
                    PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                    System.out.println("TASK SERVER: JOB QUEUE FULL!!");
                    pw.println("SERVER BUSY!!!");
                    s.close();
                }
            }
        } catch (Exception e) {
            System.out.println("SERVER SOCKET CLOSED");
        } finally {
            tm.join();
            if (!ss.isClosed()) {
                ss.close();
            }
            System.out.println("SERVER SHUT DOWN");
        }
        System.exit(0);
    }

    private static class Job implements Runnable {
        private Socket socket;
        private ThreadManager tm;
        private String cmd;

        public Job(Socket socket, String command, ThreadManager tm) {
            this.socket = socket;
            this.tm = tm;
            this.cmd = command;
            System.out.println("Worker Thread: New connection at: " + socket);
        }

        public void run() {
            try {
                boolean test = true;
                if (test) {
                    Thread.sleep(100);

                }
                String input;
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Pattern p = null;
                Matcher m = null;
                input = cmd.toUpperCase();

                String regex = "(((ADD)|(SUB)|(MUL)|(DIV)),[0-9],[0-9])|(KILL)|(EHLO)";
                p = Pattern.compile(regex);
                m = p.matcher(input);

                if (!m.matches()) {
                    out.println("Unsupported commands. Try again. Examples of possible commands are "
                            + "ADD,4,5 or SUB,3,6 or MUL,9,8 or DIV,1,1 or KILL.");
                } else {
                    processInput(input, out);
                }
            } catch (IOException e) {
                System.out.println("Worker Thread: CLIENT HANDLE ERROR: " + e);
            } catch (InterruptedException e) {
                System.out.println("Worker Thread: COULD NOT SLEEP");
;            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Worker Thread: SOCKET CLOSE ERROR");
                }
                System.out.println("Worker Thread: CONNECTION CLOSED");
            }
        }

        private void processInput(String input, PrintWriter out) {
            System.out.println("Worker Thread: Input: " + input);
            
            if (input.equals("KILL")) {
                tm.terminate();
                System.out.println("Worker Thread: KILL");
                return;
            }
            if (input.equals("ACK")) {
                out.println("CONNECTED");
                System.out.println("ACK");
                return;
            }

            int o1 = Integer.parseInt(input.charAt(4) + "");
            int o2 = Integer.parseInt(input.charAt(6) + "");
            String toRet = "";
            if (input.charAt(0) == 'A') {
                toRet = "" + (o1 + o2);
                     
            } else if (input.charAt(0) == 'S') {
                toRet = "" + (o1 - o2);
            } else if (input.charAt(0) == 'M') {
                toRet = "" + (o1 * o2);
            } else if (input.charAt(0) == 'D') {
                if(o2 == 0) {
                    toRet = "????";
                }
                else {
                    toRet = "" + (o1 / o2);
                }
            }
            out.println(toRet);
            System.out.println("Worker Thread: SENT: " + toRet);
        }
    }
}
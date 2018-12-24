import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;

public class Calculate {
    int[] number = {1,2,3,4,5,6,7,8,9};
    String[] operator = {"+","*","-","/"};
    private String question;
    private int answer;
    ScriptEngine e = new ScriptEngineManager().getEngineByName("js");
    Random rand = new Random();

    public Calculate() {
        int a = rand.nextInt(9);
        int b = rand.nextInt(9);
        int c = rand.nextInt(4);
        this.question = number[a]+operator[c]+number[b];
        try {
            this.answer = ((Number)e.eval( number[a]+operator[c]+number[b])).intValue();
        } catch (ScriptException e1) {
            e1.printStackTrace();
        }
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

}

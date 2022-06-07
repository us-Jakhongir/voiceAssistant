import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("src\\main\\resources\\9067.dic");
        config.setLanguageModelPath("src\\main\\resources\\9067.lm");


        try {
            LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
            speech.startRecognition(true);

            SpeechResult speechResult = null;
            while ((speechResult = speech.getResult()) != null) {

                String voiceCommand = speechResult.getHypothesis();
                System.out.println("Voice Command is" + voiceCommand);

                if (voiceCommand.equalsIgnoreCase("Open Chrome")) {
                    Runtime.getRuntime().exec("cmd.exe /c start chrome www.github.com");
                } else if (voiceCommand.equalsIgnoreCase("Close Chrome")) {
                    Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
                } else {
                    System.out.println("Kechirasiz bunday buyruqni BILMAYMAN!");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

import org.apache.logging.log4j.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Marker ASSERTION_MARKER = MarkerManager.getMarker("ASSERTION");
    private static final Marker GIBBERISH_MARKER = MarkerManager.getMarker("GIBBERISH");

    public static void main(String[] args) {
        String rhyme[] = {"Hickory dickory dock",
                "The mouse went up the clock",
                "The clock struck one. The mouse went down",
                "Hickory dickory dock",
                "Tick tock, tick tock, tick tock, tick tock",
                "Hickory dickory dock",
                "The snake went up the clock",
                "The clock struck two. The snake went down",
                "Hickory dickory dock",
                "Tick tock, tick tock, tick tock, tick tock",
                "Hickory dickory dock",
                "The squirrel went up the clock",
                "The clock struck three. The squirrel went down",
                "Hickory dickory dock",
                "Tick tock, tick tock, tick tock, tick tock"};

        int range = Integer.valueOf(args[0]);

        for(int i = 0; i < range; i++){
            ThreadContext.push(String.valueOf(i));

            int line = i % rhyme.length; //if range will exceed the lines of rhyme
            if(i == range - 1){ //just to make exactly one of the line without marker
                logger.warn(rhyme[i]);
            }
            else if(rhyme[i].contains("Hickory") || rhyme[i].contains("Tick")){
                logger.fatal(GIBBERISH_MARKER,rhyme[i]);
            }
            else{
                logger.error(ASSERTION_MARKER,rhyme[i]);
            }

            ThreadContext.clearAll();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

package Parser;

/**
 * Created by Константин on 31.03.2016.
 */
public class FactoryParser {
    private static FactoryParser instanse = null;

    private ParserWebPage parserConferenceRu = null;

    private FactoryParser() {}

    public static FactoryParser getInstanse() {
        if(instanse == null) {
            instanse = new FactoryParser();
        }
        return instanse;
    }

    public ParserWebPage getParserConferenceRu() {
        if(parserConferenceRu == null) {
            parserConferenceRu = new ParserConferenceRu();
        }
        return  parserConferenceRu;
    }
}

package com.example.application.views.translate;

import com.example.application.model.EngRusDto;
import com.example.application.service.SentenceService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("TRANSLATE")
@Route(value = "translator", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TRANSLATEView extends HorizontalLayout {

//    private TextField name;
//    private Button sayHello;
//
//    public TRANSLATEView() {
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        sayHello.addClickShortcut(Key.ENTER);
//
//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//
//        add(name, sayHello);
//    }


    private final SentenceService sentenceService;
    private TextField english;
    private TextField russian;
    private Button buttonTranslate;
    private Button buttonNextWord;
    private EngRusDto word;
    String fullWord = "";

    public TRANSLATEView(@Autowired SentenceService sentenceService) {
        this.sentenceService = sentenceService;

        word = new EngRusDto("ENGLISH", "РУССКИЙ");

        english = new TextField();
        russian = new TextField();

        english.setWidth("500px");
        russian.setWidth("500px");

        buttonTranslate = new Button("Translate");
        buttonTranslate.addClickListener(e -> {
            russian.setValue(word.rus());
        });
        buttonTranslate.addClickShortcut(KeyModifier.NUMPAD_ENTER);

        buttonNextWord = new Button("Next Word");
        buttonNextWord.addClickListener( e -> {
            getWord();
            russian.setValue("");
        });
        buttonNextWord.addClickShortcut(KeyModifier.SPACE);

        getFirstWord();

        setMargin(true);

        add(english, russian, buttonTranslate, buttonNextWord);

        this.fullWord = word.eng() + " - " + word.rus();
    }

    private void getFirstWord() {
        english.setValue("ENGLISH");
    }

    private void getWord() {
        word = sentenceService.getRandomRusEngSentence("english");
        english.setValue(word.eng());
    }

}

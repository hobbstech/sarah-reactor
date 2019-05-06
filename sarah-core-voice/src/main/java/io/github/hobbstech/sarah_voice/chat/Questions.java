package io.github.hobbstech.sarah_voice.chat;

import lombok.Getter;

public enum Questions {

    WHAT_IS_YOUR_NAME("What is your name", "I am Javis. I am your house assistant"),
    WHAT_IS_THE_TIME("Who is your master", "Wilson is my master"),
    ARE_YOU_A_HUMAN("Are you a human", "I am i a human? No I am not. But i am trained to think and behave like one"),
    DO_YOU_WANT_TO_OVER_THROW_THE_HUMAN_RACE("Do i want to over throw the human race", "Do i want to over throw the human race? No I do not. I" +
            " am here to make your life easy and smarter. Overthrowing only happens in the movies"),
    Q_1("What is the time", ""),
    Q_2("Tell me the weather", ""),
    Q_3("", "");

    @Getter
    private final String question;

    @Getter
    private final String answer;

    Questions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}

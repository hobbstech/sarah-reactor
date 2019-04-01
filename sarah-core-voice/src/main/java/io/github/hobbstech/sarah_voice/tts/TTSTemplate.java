package io.github.hobbstech.sarah_voice.tts;

import lombok.Getter;
import lombok.val;
import marytts.modules.synthesis.Voice;
import marytts.signalproc.effects.BaseAudioEffect;
import marytts.signalproc.effects.VolumeEffect;

import java.util.Collection;
import java.util.HashSet;

public class TTSTemplate {

    private static TTSTemplate defaultInstance = new TTSTemplate();

    @Getter
    private final TextToSpeech textToSpeech;

    public static TTSTemplate getDefaultInstance(Integer volume, Integer baseAudio) {
        defaultInstance.setVolume(volume);
        defaultInstance.setBaseAudio(baseAudio);
        return defaultInstance;
    }

    public static TTSTemplate getDefaultInstance() {
//        defaultInstance.setVolume(2);
//        defaultInstance.setBaseAudio(10);
        return defaultInstance;
    }

    private TTSTemplate() {

        textToSpeech = new TextToSpeech();

        val voices = textToSpeech.getAvailableVoices();
        //.stream().filter(voice -> voice.gender().equals(FEMALE)).collect(toSet());

        Voice defaultVoice = voices.stream().filter(voice -> voice.hasName("cmu-bdl-hsmm")).findFirst()
                .orElseGet(() -> voices.stream().findAny().get());

        textToSpeech.setVoice(defaultVoice.getName());

    }

    private void setVolume(Integer value) {
        val volumeEffects = new VolumeEffect();
        volumeEffects.setParams("amount:" + value);
        textToSpeech.getMarytts().setAudioEffects(volumeEffects.getFullEffectAsString());
    }

    private void setBaseAudio(Integer value) {
        BaseAudioEffect baseAudioEffect = new BaseAudioEffect(8000);
        baseAudioEffect.setParams("amount:" + value);
        textToSpeech.getMarytts().setAudioEffects(baseAudioEffect.getFullEffectAsString());
    }

    public void speak(Collection<String> words) {
        words.forEach(word -> textToSpeech.speak(word, 2.0f, false, true));
    }

    public void speak(String word) {
        val words = new HashSet<String>();
        words.add(word);
        speak(words);
    }

}

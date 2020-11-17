package kostki;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MusicPlayer {
    AudioClip mediaPlayer;
    int songIndex = 0;
    ArrayList<String> songs;
    GameScreenController gsc;
    public MusicPlayer(GameScreenController gameScreenController){
        gsc = gameScreenController;
        try (Stream<Path> walk = Files.walk(Paths.get(System.getProperty("user.dir")))) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            result.removeIf(str -> !str.contains(".mp3"));
            songs = (ArrayList<String>)result;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void playMusic(){
        if(!songs.isEmpty()) {
            if (songIndex == songs.size() - 1) {
                gsc.nextSong.setDisable(true);
            } else if (songIndex == 0) {
                gsc.prevSong.setDisable(true);
            } else if (songIndex > 0 && songIndex != songs.size() - 1) {
                gsc.prevSong.setDisable(false);
                gsc.nextSong.setDisable(false);
            }
            stop();
            String bip = songs.get(songIndex);
            Media hit = new Media(Paths.get(bip).toUri().toString());
            mediaPlayer = new AudioClip(hit.getSource());
            mediaPlayer.play();
        }
    }
    public void stop(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
    }

    public void next() {
        stop();
        if(!songs.isEmpty() && songIndex != songs.size()-1){
            songIndex++;
            playMusic();
        }
    }

    public void prev() {
        stop();
        if(!songs.isEmpty() && songIndex > 0){
            songIndex--;
            playMusic();
        }
    }
}

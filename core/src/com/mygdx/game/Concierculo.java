package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Concierculo implements Screen {
    final Drop game;

    ShapeRenderer shapeRenderer;
    
    Texture background;
    Texture play, stop;

    Sound animal1, animal2, animal3, animal4, animal5, animal6, animal7, animal8;

    Array<Rectangle> baterias;
    Rectangle playButton;
    Music music;

    OrthographicCamera camera;

    int cameraWidth = 800, cameraHeight = 480;
    boolean isPlaying;
    long lastTouched;

    public Concierculo(final Drop gam) {
        this.game = gam;

        shapeRenderer = new ShapeRenderer();

        play = new Texture(Gdx.files.internal("play.png"));
        stop = new Texture(Gdx.files.internal("stop.png"));
        background = new Texture(Gdx.files.internal("bat3.png"));

        playButton = new Rectangle(cameraWidth/2-30, cameraHeight -80, 64, 64);

        music = Gdx.audio.newMusic(Gdx.files.internal("songs/bass.mp3"));
        music.setLooping(true);
        music.play();
        iniciaMusica();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);


        baterias = new Array<Rectangle>();

        rellenaCulos();
        isPlaying=false;

    }


    public void iniciaMusica(){
        animal1 = Gdx.audio.newSound(Gdx.files.internal("songs/animal1.mp3"));
        animal2 = Gdx.audio.newSound(Gdx.files.internal("songs/animal2.mp3"));
        animal3 = Gdx.audio.newSound(Gdx.files.internal("songs/animal3.mp3"));
        animal4 = Gdx.audio.newSound(Gdx.files.internal("songs/animal4.mp3"));
        animal5 = Gdx.audio.newSound(Gdx.files.internal("songs/animal5.mp3"));
        animal6 = Gdx.audio.newSound(Gdx.files.internal("songs/animal6.mp3"));
        animal7 = Gdx.audio.newSound(Gdx.files.internal("songs/animal7.mp3"));
        animal8 = Gdx.audio.newSound(Gdx.files.internal("songs/animal8.mp3"));
    }

    public void rellenaCulos(){
        //3 de abajo
        baterias.add(new Rectangle(40, 0, 190, 140));
        baterias.add(new Rectangle(300, 0, 220, 160));
        baterias.add(new Rectangle(570, 0, 220, 160));

        //platillo izquierdo
        baterias.add(new Rectangle(10, 160, 190, 130));

        //dos de enmedio
        baterias.add(new Rectangle(230, 230, 130, 130));
        baterias.add(new Rectangle(430, 240, 140, 130));

        // dos de arriba
        baterias.add(new Rectangle(0, 340, 240, 130));
        baterias.add(new Rectangle(600, 260, 200, 230));
    }

    public void paraCancion(){
        if (music.isLooping())
            music.stop();
        isPlaying=false;
    }

    public void cambiaCancion(){
        music.setLooping(true);
        music.play();
        isPlaying=true;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0, cameraWidth, cameraHeight);


        game.font.getData().setScale(2);

        if(isPlaying){
            game.batch.draw(stop, playButton.x, playButton.y, playButton.width, playButton.height);
        }else{
            game.batch.draw(play, playButton.x, playButton.y, playButton.width, playButton.height);
        }

        game.batch.end();


        if (Gdx.input.isTouched()) {
            if (TimeUtils.nanoTime() - lastTouched >  180000000){
                lastTouched = TimeUtils.nanoTime();
                Vector3 touchPos3 = new Vector3();
                touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos3);

                if(playButton.contains(touchPos3.x, touchPos3.y)){
                    if (isPlaying)
                        paraCancion();
                    else
                        cambiaCancion();
                }

                if(baterias.get(0).contains(touchPos3.x, touchPos3.y)){
                    animal1.play();
                }
                if(baterias.get(1).contains(touchPos3.x, touchPos3.y)){
                    animal2.play();
                }
                if(baterias.get(2).contains(touchPos3.x, touchPos3.y)){
                    animal3.play();
                }
                if(baterias.get(3).contains(touchPos3.x, touchPos3.y)){
                    animal4.play();
                }
                if(baterias.get(4).contains(touchPos3.x, touchPos3.y)){
                    animal5.play();
                }
                if(baterias.get(5).contains(touchPos3.x, touchPos3.y)){
                    animal6.play();
                }
                if(baterias.get(6).contains(touchPos3.x, touchPos3.y)){
                    animal7.play();
                }
                if(baterias.get(7).contains(touchPos3.x, touchPos3.y)){
                    animal8.play();
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
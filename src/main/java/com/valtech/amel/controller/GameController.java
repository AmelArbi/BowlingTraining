package com.valtech.amel.controller;

import com.valtech.amel.model.Frame;
import com.valtech.amel.model.Game;

public class GameController {
    public void wurfelnAccept(int zahl, Game game) {
        if (game.getIteration() <= 9) {
            if (game.getFrames().size() == game.getIteration()) {
                wuerfelnAcceptNewFrame(zahl,game);
            } else {
                wuerfelnAcceptExistingFrame(zahl, game);
            }
        }
    }

    private void wuerfelnAcceptNewFrame(int zahl, Game game) {
        Frame frame = new Frame(game.getIteration());
        frame.addThrow(zahl);
        game.getFrames().add(frame);

        if (frame.isComplete()) {
            addBonus(game);
            game.setIteration(game.getIteration() + 1);
        }
    }

    private void wuerfelnAcceptExistingFrame(int zahl, Game game) {
        Frame currentFrame = game.getFrames().get(game.getIteration());
        currentFrame.addThrow(zahl);

        if (currentFrame.isComplete()) {
            addBonus(game);
            game.setIteration(game.getIteration() + 1);
        }
    }

    public void addBonus(Game game) {
        Frame currentFrame = game.getFrames().get(game.getIteration());
        if (game.getIteration() > 0) {
            Frame prevFrame = game.getFrames().get(game.getIteration() - 1);
            addBonus(game, currentFrame, prevFrame);
        }
    }

    private void addBonus(Game game, Frame currentFrame, Frame prevFrame) {
        if (game.getIteration() >= 2) {
            Frame prePrevFrame = game.getFrames().get(game.getIteration() - 2);
            if (prePrevFrame.isStrike() && prevFrame.isStrike()) {
                prePrevFrame.addBonus(currentFrame.getThrow(0));
            }
        }
        if (prevFrame.isSpare()) {
            prevFrame.addBonus(currentFrame.getThrow(0));
        } else if ((prevFrame.isStrike())) {
            prevFrame.addBonus(currentFrame.getThrow(0));
            if (game.getIteration() < 9) {

                if (!currentFrame.isStrike()) {
                    prevFrame.addBonus(currentFrame.getThrow(1));
                }
            } else if (game.getIteration() == 9) {
                prevFrame.addBonus(currentFrame.getThrow(1));

            }
        }
    }

    public int calculateScore(Game game, int iteration) {
        int sum = 0;
        for (int i = 0; i < iteration; i++) {
            sum += game.getFrames().get(i).getFinalScore();
        }
        return sum;

    }

}

package Chen.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PoofEffect extends AbstractGameEffect {
    private float x;
    private float y;

    public PoofEffect(float x, float y) {
        this.x = x;
        this.y = y;
        this.duration = 0.4F;
    }

    public void update() {
        if (this.duration == 0.4F) {
            CardCrawlGame.sound.play("ATTACK_WHIFF_2");

            for(int i = 0; i < 60; ++i) {
                AbstractDungeon.effectsQueue.add(new PuffBlurEffect(this.x, this.y));
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}

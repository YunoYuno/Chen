package Chen.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.GenericSmokeEffect;

public class CustomVerticalImpactEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final float DUR = 0.6F;
    private TextureAtlas.AtlasRegion img;
    private boolean playedSound = false;

    private Color color2;
    private String[] sounds;

    public CustomVerticalImpactEffect(float x, float y, String[] sfx, Color back, Color main) {
        this.img = ImageMaster.VERTICAL_IMPACT;
        this.x = x - (float)this.img.packedWidth / 2.0F;
        this.y = y - (float)this.img.packedHeight * 0.01F;
        this.startingDuration = DUR;
        this.duration = DUR;
        this.scale = Settings.scale;
        this.rotation = MathUtils.random(40.0F, 50.0F);
        this.color = back.cpy();
        this.color2 = main.cpy();
        this.sounds = sfx;
        this.renderBehind = false;

        for(int i = 0; i < 50; ++i) {
            AbstractDungeon.effectsQueue.add(new GenericSmokeEffect(x + MathUtils.random(-280.0F, 250.0F) * Settings.scale, y - 80.0F * Settings.scale));
        }
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        if (this.duration < 0.5F && !this.playedSound) {
            for (String sound : sounds)
            {
                CardCrawlGame.sound.playA(sound, 0.0F);
            }
            this.playedSound = true;
        }

        if (this.duration > 0.2F) {
            this.color.a = Interpolation.fade.apply(0.5F, 0.0F, (this.duration - 0.34F) * 5.0F);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration * 5.0F);
        }

        this.scale = Interpolation.fade.apply(Settings.scale * 1.1F, Settings.scale * 1.05F, this.duration / 0.6F);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.3F, this.scale * 0.8F, this.rotation - 18.0F);
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.3F, this.scale * 0.8F, this.rotation + MathUtils.random(12.0F, 18.0F));
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.4F, this.scale * 0.5F, this.rotation - MathUtils.random(-10.0F, 14.0F));
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.7F, this.scale * 0.9F, this.rotation + MathUtils.random(20.0F, 28.0F));
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 1.5F, this.scale * MathUtils.random(1.4F, 1.6F), this.rotation);

        color2.a = this.color.a;
        sb.setColor(color2);
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale * MathUtils.random(0.8F, 1.2F), this.rotation);
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale * MathUtils.random(0.4F, 0.6F), this.rotation);
        sb.draw(this.img, this.x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, (float)this.img.packedWidth / 2.0F, 0.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.5F, this.scale * 0.7F, this.rotation + MathUtils.random(20.0F, 28.0F));
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
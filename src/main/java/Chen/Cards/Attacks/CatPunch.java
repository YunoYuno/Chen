package Chen.Cards.Attacks;

import Chen.Abstracts.ShiftChenCard;
import Chen.Util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Chen.ChenMod.makeID;

public class CatPunch extends ShiftChenCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CatPunch",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON
    );

    public final static String ID = makeID(cardInfo.cardName);

    private final static int DAMAGE = 6;
    private final static int UPG_DAMAGE = 2;

    private final static int BLOCK = 4;
    private final static int UPG_BLOCK = 2;

    public CatPunch()
    {
        super(cardInfo, false);

        setDamage(DAMAGE, DAMAGE, UPG_DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, 0, UPG_BLOCK, 0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Form) //human
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
        else //cat
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }
}
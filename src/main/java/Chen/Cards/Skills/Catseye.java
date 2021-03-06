package Chen.Cards.Skills;

import Chen.Abstracts.BaseCard;
import Chen.Powers.CatseyePower;
import Chen.Util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Chen.ChenMod.makeID;

public class Catseye extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Catseye",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON
    );

    public final static String ID = makeID(cardInfo.cardName);

    private final static int UPG_COST = 0;
    private final static int BUFF = 1;

    public Catseye()
    {
        super(cardInfo, false);

        setCostUpgrade(UPG_COST);
        setMagic(BUFF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO: Make power. Stacking: Deals bonus damage = tear * power amount
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CatseyePower(p, this.magicNumber), this.magicNumber));
    }
}
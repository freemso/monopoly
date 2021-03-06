package card;

import player.Player;
import spot.AbstractSpot;
import util.Game;

/**
 * Created by freemso on 2016/4/25.
 */
public class BarricadeCard extends AbstractCard {
    public BarricadeCard() {
        super("路障卡", 6);
    }

    @Override
    public boolean effect(Game game, Player user) {
        AbstractSpot spot = user.selectSpot(game);
        if (game.getMapSystem().calcDistance(spot.getId(), user.getPosition().getId()) > 8) {
            game.getUI().showMessage("不在范围内！请重新选择");
            return effect(game, user);
        }
        if (spot.hasBarricade()) {
            game.getUI().showMessage("此处已有路障，不能再次放置！");
            return effect(game, user);
        }
        game.getUI().showMessage("路障已成功放置！");
        spot.addBarricade();
        return true;
    }
}

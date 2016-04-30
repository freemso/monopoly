package kernel.card;

import kernel.Game;
import kernel.Player;
import kernel.spot.AbstractSpot;
import kernel.spot.HouseSpot;
import kernel.map.Street;
import kernel.util.SpotSystem;

/**
 * Created by freemso on 2016/4/25.
 */
public class DemolitionCard extends AbstractCard {
    public DemolitionCard() {
        super("拆迁卡", 6);
    }

    @Override
    public boolean effect(Game game, Player user) {
        if(user.getPosition().getType() != SpotSystem.Type.HOUSE) {
            game.getUI().popMessage("你所在的位置不属于任何街道，无法使用拆迁卡！");
            return false;
        } else {
            Street street = ((HouseSpot) user.getPosition()).getStreet();
            street.demolition();
            game.getUI().showMessage("已经成功使用拆迁卡，" + street.getName() + "上所有的房屋全部被拆除，补偿款已发给各个房主");
            return true;
        }
    }
}

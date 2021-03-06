package card;

import player.Player;
import spot.AbstractSpot;
import spot.HouseSpot;
import spot.SpotType;
import util.Game;

/**
 * Created by freemso on 2016/4/25.
 */
public class BuyHouseCard extends AbstractCard {
    public BuyHouseCard() {
        super("购房卡", 10);
    }

    @Override
    public boolean effect(Game game, Player user) {
        AbstractSpot spot = user.getPosition();
        // check whether the spot that the user is standing on is a house
        if (spot.getType() != SpotType.HOUSE) {
            // it is not a building
            game.getUI().showMessage("这不是一个房屋，你不能使用购房卡！");
            return false;
        }
        // check the owner of the house
        if (((HouseSpot) spot).getOwner() == user) {
            // this is the user's house
            game.getUI().showMessage("这是你自己的房屋，无法再次购买！");
            return false;
        }
        double price = ((HouseSpot) spot).calcPrice();
        if (game.getUI().confirm("这个房屋的价格为 " + price + " 元，是否使用购房卡购买？")) {
            if (user.getCash() >= price) {
                user.subCash(price);
                user.addHouse((HouseSpot) spot);
                ((HouseSpot) spot).setOwner(user);
                if (((HouseSpot) spot).getOwner() != null) {
                    Player owner = ((HouseSpot) spot).getOwner();
                    owner.addCash(price);
                    owner.removeHouse((HouseSpot) spot);
                }
                game.getUI().showMessage("你已成功使用购房卡购买" + spot.getName());
                return true;
            }
        }
        return false;
    }
}

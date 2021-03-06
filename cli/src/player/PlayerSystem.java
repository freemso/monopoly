package player;

import util.Game;

import java.util.ArrayList;

/**
 * Created by freemso on 2016/4/30.
 */
public class PlayerSystem {
    private final double originalCash = 2000;
    private final double originalDeposit = 2000;
    private final int originalTicket = 10;

    private ArrayList<Player> playerList;

    public PlayerSystem() {
        this.playerList = new ArrayList<>();
    }

    public void showPlayerInfo(Game game) {
        game.getUI().showMessage("玩家资产信息如下：\n");
        System.out.printf("%-9s%-10s%-10s%-10s%-9s%-7s%-8s\n", "玩家名", "点券", "现金", "存款", "房产数", "房产总价值", "资产总额");
        for (Player p : playerList) {
            System.out.printf("%-12s%-12d%-12.2f%-12.2f%-12d%-12.2f%-12.2f\n",
                    p.getName(), p.getTicket(),
                    p.getCash(), p.getDeposit(),
                    p.getHouseList().size(), p.getHouseValue(),
                    p.getCapital());
        }
    }

    public void initPlayer(Game game) {
        int playerNumber = game.getUI().inputInt("请输入玩家个数（ 2 - 4 ）：", 2, 4);
        for (int i = 0; i < playerNumber; i++) {
            String name = game.getUI().inputStr("请输入玩家 " + (i+1) + " 的昵称：", 2, 10);
            Player player = new Player(i, name, originalCash, originalDeposit, originalTicket);
            playerList.add(player);
            game.getMapSystem().addPlayer(player);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public Player mostHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getHouseList().size() > temp.getHouseList().size()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public Player leastHousePlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getHouseList().size() < temp.getHouseList().size()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public Player maxCapitalPlayer() {
        Player temp = playerList.get(0);
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getCapital() > temp.getHouseList().size()) {
                temp = playerList.get(i);
            }
        }
        return temp;
    }

    public void bankrupt(Game game, Player player) {
        player.bankrupt();
        int count = 0;
        for (Player p: playerList) {
            if (!p.isBankrupt()) count++;
        }
        if (count == 1) {
            playerList.stream().filter(p -> !p.isBankrupt()).forEach(p -> {
                game.getUI().showMessage("游戏结束！\n" + p.getName() + "获得胜利");
                System.exit(0);
            });
        }
    }

}

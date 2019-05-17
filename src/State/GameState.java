package State;

import Game.*;
import State.Utils.PlayerBar;
import State.Utils.Levels.*;

import UI.UIManager;
import World.World;
import java.awt.*;

import static State.Utils.Levels.*;
import static State.Utils.Levels.Level.*;


public class GameState extends State {
    private World world;
    private PlayerBar playerBar;
    public static Level current_level = level_1;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, current_level);
        handler.setWorld(world);
        this.playerBar = new PlayerBar();
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick() {
        if (handler.getWorld().getEntityManager().getCounter() == 0){
            if (current_level == level_4){
                State.setState(handler.getGame().winState);
            }
            else{
                current_level = nextLevel(current_level);
                String path = GetLevelWorld(current_level);
                world.setWorld(path);
                State.setState(handler.getGame().intermediateState);
            }
        }

        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }
        world.tick();
        playerBar.tick(handler.getWorld().getEntityManager().getPlayer().getHealth(), handler.getWorld().getEntityManager().getCounter(),handler.getWorld().getEntityManager().getPlayer().getNumberOfCoins() );
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        playerBar.render(g);

        g.drawString(GetLevelName(current_level), handler.getWidth()/2-30, 30);
    }

}

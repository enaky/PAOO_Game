package Entity.Creature;

import Entity.Current_Direction;
import Entity.Types.Entity_Types;
import Game.Animation;
import Game.Assets;
import Game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyWithAnimations extends Enemy {
    protected Animation animationUp, animationDown, animationLeft, animationRight;

    public EnemyWithAnimations(Handler handler, float x, float y, Entity_Types.Tank_Type tank_type) {
        super(handler, x, y, tank_type);
        animationUp = new Animation(const_speed, Assets.robot[0]);
        animationRight = new Animation(const_speed, Assets.robot[1]);
        animationDown = new Animation(const_speed, Assets.robot[2]);
        animationLeft = new Animation(const_speed, Assets.robot[3]);
    }
    @Override public void tick(){
        super.tick();
        animationDown.tick();
        animationLeft.tick();
        animationRight.tick();
        animationUp.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width*3/2, height*3/2, null);
//        g.setColor(Color.YELLOW);
//        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    @Override
    protected BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return animationLeft.getCurrentFrame();
        }
        else if(xMove > 0){
            return animationRight.getCurrentFrame();

        }
        else if (yMove < 0){
            return animationUp.getCurrentFrame();
        }
        else if (yMove > 0){
            return animationDown.getCurrentFrame();
        }
        else{
            if (current_direction == Current_Direction.up){
                return Assets.robot[0][12];
            }
            if (current_direction == Current_Direction.right){
                return Assets.robot[1][12];
            }
            if (current_direction == Current_Direction.down){
                return Assets.robot[2][12];
            }
            return Assets.robot[3][12];
        }
    }

}
package dragonball.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;

		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a
		// previous battle
		if (me instanceof Saiyan) {
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public BattleOpponent getDefender() {
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks() {
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn() {
		attacker = getDefender();
	}

	public void endTurn() throws InterruptedException {
		// reset block mode
		if (attacker == me && foeBlocking) {
			foeBlocking = false;
		} else if (attacker == foe && meBlocking) {
			meBlocking = false;
		}

		// if i'm dead
		if (((Fighter) me).getHealthPoints() == 0) {
			// tell everyone my opponent won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED,
					foe));
			// if my opponent is dead
		} else if (((Fighter) foe).getHealthPoints() == 0) {
			// tell everyone i won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
		} else {
			switchTurn();

			getAttacker().onDefenderTurn();
			getDefender().onAttackerTurn();

			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
		}
	}

	public void start() throws InterruptedException {
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}

	// used to automate turn for opponent a.k.a. ai
	public void play() throws NotEnoughKiException, InterruptedException {
		Fighter f = (Fighter) foe;
		SuperAttack s = new SuperAttack("I failed", 0);
		UltimateAttack u = new UltimateAttack("I failed", 0);
		boolean hasMaxCharge = false;
		boolean hasSuperSaiyan = false;

		for (int i = 0; i < f.getSuperAttacks().size(); i++) {
			if (f.getSuperAttacks().get(i).getDamage() > s.getDamage()) {
				s = f.getSuperAttacks().get(i);
			}
			if (f.getSuperAttacks().get(i) instanceof MaximumCharge) {
				hasMaxCharge = true;
			}
		}

		for (int i = 0; i < f.getUltimateAttacks().size(); i++) {
			if (f.getUltimateAttacks().get(i).getDamage() > u.getDamage()) {
				u = f.getUltimateAttacks().get(i);
			}
			if (f.getUltimateAttacks().get(i) instanceof SuperSaiyan) {
				hasSuperSaiyan = true;
			}
		}

		if (meBlocking) {
			if (hasMaxCharge && f.getKi() < f.getMaxKi() - 1) {
				attack(new MaximumCharge());
			} else if (f.getKi() > 2 && hasSuperSaiyan) {
				attack(new SuperSaiyan());
			} else if ((Fighter) me instanceof Namekian) {
				useStrongestAttack(s, u);
			} else if ((Fighter) me instanceof Earthling) {
				if (((Fighter) me).getBlastDamage() >= ((Fighter) me)
						.getPhysicalDamage()
						&& !((Fighter) me).getSuperAttacks().isEmpty()
						&& !((Fighter) me).getUltimateAttacks().isEmpty()) {
					useStrongestAttack(s, u);
				}
				else{
					attack(new PhysicalAttack());
				}
			} else {
				attack(new PhysicalAttack());
			}
		} else {
			useStrongestAttack(s, u);
		}

	}

	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException, InterruptedException {
		attack.onUse(attacker, getDefender(), (attacker == me && foeBlocking)
				|| (attacker == foe && meBlocking));

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK,
				attack));

		endTurn();
	}

	// perform a block and end turn
	public void block() throws InterruptedException {
		if (attacker == me) {
			meBlocking = true;
		} else if (attacker == foe) {
			foeBlocking = true;
		}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn
	public void use(Player player, Collectible collectible)
			throws NotEnoughSenzuBeansException, InterruptedException {
		switch (collectible) {
		case SENZU_BEAN:
			if (player.getSenzuBeans() > 0) {
				PlayableFighter activeFighter = player.getActiveFighter();
				activeFighter.setHealthPoints(activeFighter
						.getMaxHealthPoints());
				activeFighter.setStamina(activeFighter.getMaxStamina());

				player.setSenzuBeans(player.getSenzuBeans() - 1);

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.USE,
						collectible));
			} else {
				throw new NotEnoughSenzuBeansException();
			}
			break;
		default:
			break;
		}

		endTurn();
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e) throws InterruptedException {
		if (listener != null) {
			listener.onBattleEvent(e);
		}
	}

	public void useStrongestAttack(SuperAttack s, UltimateAttack u)
			throws NotEnoughKiException, InterruptedException {
		Fighter f = (Fighter) foe;
		if (f.getKi() > 2 && u.getDamage() > 0) {
			attack(u);
		} else if (f.getKi() > 0 && s.getDamage() > 0) {
			attack(s);
		} else {
			attack(new PhysicalAttack());
		}

	}
}

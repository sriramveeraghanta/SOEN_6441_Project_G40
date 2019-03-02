package launcher;

import java.awt.EventQueue;

import controllers.GameController;

/**
 * This class creating the game objects and binds game models with game views by
 * interacting with the game GameController.
 * 
 * @author Sriram Veeraghanta
 *
 */
public class Launcher {
	/*
	 * This is the main method.
	 * */
	public static void main(String args[]) {
		// Logs
		System.out.println("Start Game");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController controller = new GameController();
					controller.showHomeView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("end");
	}
}

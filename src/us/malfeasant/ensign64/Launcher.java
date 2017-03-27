package us.malfeasant.ensign64;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Launcher());
	}
	
	private final DefaultListModel<MachineDescriptor> machineList;
	//private final JList<MachineDescriptor> machineList;
	
	private Launcher() {
		JFrame frame = new JFrame("Ensign 64");
		
		machineList = new DefaultListModel<>();
		JList<MachineDescriptor> list = new JList<>(machineList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(new JScrollPane(list));
		
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem fileNew = new JMenuItem("New");
		fileNew.addActionListener((e) -> create());
		
		fileMenu.add(fileNew);
		mb.add(fileMenu);
		frame.setJMenuBar(mb);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void create() {
		String name = JOptionPane.showInputDialog("Enter a name:");
		machineList.addElement(new MachineDescriptor(name));
	}
}

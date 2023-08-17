package view;

import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StokView<JPanel> extends JFrame {

	private JPanel contentPane;
	private JTextField tfStokKodu;
	private JTextField tfStokAdı;
	private JTextField tfBarkodu;
	private JTable table;
	private JTextArea taAciklama;
	private JComboBox cbStokTipi;
	private JComboBox cbBirimi;
	private JComboBox cbKdvTipi;
	private JButton btnSil;
	private JTextField tfOlusturmaTarihi;
	private static JTable tbTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StokView frame = new StokView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
	
	
//            ARRAYLİST ARAMA İÇİN

	public ArrayList<StokModel> getStokKarts() {
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement = null;
		ResultSet resultSet;
		ArrayList<StokModel> stokKarts = null;
		try {
			connection = helper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"select * from mydb.stokkart Where stokkart.stokKodu LIKE '" + tfArama.getText() + "%'");
			stokKarts = new ArrayList<StokModel>();
			while (resultSet.next() == true) {
				stokKarts.add(new StokModel(resultSet.getString("stokKodu"), resultSet.getString("stokAdı"),
						resultSet.getInt("stokTipi"), resultSet.getString("birimi"), resultSet.getString("barkodu"),
						resultSet.getDouble("kdvTipi"), resultSet.getString("acıklama"),
						resultSet.getString("olusturmaTarihi")));
			}
		} catch (SQLException e) {
			helper.showErrorMessage(e);
		} finally {
		}
		return stokKarts;
	}

	
	
	
	DefaultTableModel model = new DefaultTableModel();
	private JTextField tfKopyala;
	protected JTextField tfArama;

	/**
	 * Create the frame.
	 */
	public StokView() {
		getContentPane().setLayout(null);

		JLabel lblStokKodu = new JLabel("Stok kodunu girin:");
		lblStokKodu.setBounds(10, 38, 120, 14);
		getContentPane().add(lblStokKodu);

		tfStokKodu = new JTextField();
		tfStokKodu.setBounds(132, 35, 147, 20);
		getContentPane().add(tfStokKodu);
		tfStokKodu.setColumns(10);

		JLabel lblStokAdı = new JLabel("Stok adını girin:");
		lblStokAdı.setBounds(10, 80, 120, 14);
		getContentPane().add(lblStokAdı);

		tfStokAdı = new JTextField();
		tfStokAdı.setBounds(132, 77, 147, 20);
		getContentPane().add(tfStokAdı);
		tfStokAdı.setColumns(10);

		JLabel lblStokTipi = new JLabel("Stok tipini seçin:");
		lblStokTipi.setBounds(10, 117, 120, 14);
		getContentPane().add(lblStokTipi);

		JLabel lblBirimi = new JLabel("Birimini seçin:");
		lblBirimi.setBounds(10, 160, 120, 14);
		getContentPane().add(lblBirimi);

		JLabel lblBarkodu = new JLabel("Barkodunu girin:");
		lblBarkodu.setBounds(10, 202, 120, 14);
		getContentPane().add(lblBarkodu);

		cbStokTipi = new JComboBox();
		cbStokTipi.setBounds(132, 113, 147, 22);
		getContentPane().add(cbStokTipi);
		cbStokTipi.addItem(1);
		cbStokTipi.addItem(2);
		cbStokTipi.addItem(3);

		tfBarkodu = new JTextField();
		tfBarkodu.setBounds(132, 199, 147, 20);
		getContentPane().add(tfBarkodu);
		tfBarkodu.setColumns(10);
		
		
		cbBirimi = new JComboBox();
		cbBirimi.addItem("A");
		cbBirimi.addItem("B");
		cbBirimi.addItem("C");
		cbBirimi.addItem("D");
		cbBirimi.setBounds(132, 156, 147, 22);
		getContentPane().add(cbBirimi);

		JLabel lblKdvTipi = new JLabel("Kdv tipi seçin:");
		lblKdvTipi.setBounds(10, 246, 120, 14);
		getContentPane().add(lblKdvTipi);

		cbKdvTipi = new JComboBox();
		cbKdvTipi.setBounds(132, 242, 147, 22);
		getContentPane().add(cbKdvTipi);
		cbKdvTipi.addItem(1.0);
		cbKdvTipi.addItem(8.0);
		cbKdvTipi.addItem(18.0);

		taAciklama = new JTextArea();
		taAciklama.setBounds(132, 287, 147, 20);
		getContentPane().add(taAciklama);

		tfOlusturmaTarihi = new JTextField();
		tfOlusturmaTarihi.setBounds(132, 333, 147, 20);
		getContentPane().add(tfOlusturmaTarihi);
		tfOlusturmaTarihi.setColumns(10);

		// KAYDET BUTONU
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con= null;
				DbHelper helper = new DbHelper();
				try {
					con = helper.getConnection();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String query = "insert into stokkart() values(?,?,?,?,?,?,?,?)";

				try {

					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, tfStokKodu.getText());
					stmt.setString(2, tfStokAdı.getText());
					stmt.setObject(3, cbStokTipi.getSelectedItem());
					stmt.setObject(4, cbBirimi.getSelectedItem());
					stmt.setObject(5, tfBarkodu.getText());
					stmt.setObject(6, cbKdvTipi.getSelectedItem());
					stmt.setString(7, taAciklama.getText());
					stmt.setString(8, tfOlusturmaTarihi.getText());
					stmt.executeUpdate();
					stmt.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				TabloGuncelle.tabloGuncelle();

			}
		});
		btnKaydet.setBounds(10, 383, 101, 23);
		getContentPane().add(btnKaydet);

		// SİL BUTONU

		btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String query = "delete from stokkart where StokKodu=?";

				try {
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, tfStokKodu.getText());
					stmt.executeUpdate();
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TabloGuncelle.tabloGuncelle();

			}
		});
		btnSil.setBounds(121, 383, 109, 23);
		getContentPane().add(btnSil);

		JLabel lblAciklama = new JLabel("Açıklama:");
		lblAciklama.setBounds(10, 293, 120, 14);
		getContentPane().add(lblAciklama);

		JLabel lblOlusturmaTarihi = new JLabel("Oluşturma Tarihi:");
		lblOlusturmaTarihi.setBounds(10, 336, 120, 14);
		getContentPane().add(lblOlusturmaTarihi);

		// GÜNCELLEME

		JButton btnguncelle = new JButton("Güncelle");
		btnguncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String query = "update stokkart set stokAdı=?, stokTipi=?, birimi=?, barkodu=?, kdvTipi=?, acıklama=?, olusturmaTarihi=? where stokKodu=?";

				try {

					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, tfStokAdı.getText());
					stmt.setObject(2, cbStokTipi.getSelectedItem());
					stmt.setObject(3, cbBirimi.getSelectedItem());
					stmt.setObject(4, tfBarkodu.getText());
					stmt.setObject(5, cbKdvTipi.getSelectedItem());
					stmt.setString(6, taAciklama.getText());
					stmt.setString(7, tfOlusturmaTarihi.getText());
					stmt.setString(8, tfStokKodu.getText());

					stmt.executeUpdate();
					stmt.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				TabloGuncelle.tabloGuncelle();

			}
		});
		
		
		btnguncelle.setBounds(240, 383, 101, 23);
		getContentPane().add(btnguncelle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(378, 55, 772, 416);
		getContentPane().add(scrollPane);

		
		
		
		
		
		
		// TIKLAYINCA COMPONENTLERDE GÖSTERME

		tbTable = new JTable();
		tbTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
				int selectedRowIndex = tbTable.getSelectedRow();
				tfStokKodu.setText((String) model.getValueAt(selectedRowIndex, 0));
				tfStokAdı.setText((String) model.getValueAt(selectedRowIndex, 1));
				cbStokTipi.setSelectedItem(model.getValueAt(selectedRowIndex, 2));
				cbBirimi.setSelectedItem((String) model.getValueAt(selectedRowIndex, 3));
				tfBarkodu.setText((String) model.getValueAt(selectedRowIndex, 4));
				cbKdvTipi.setSelectedItem(model.getValueAt(selectedRowIndex, 5));
				taAciklama.setText((String) model.getValueAt(selectedRowIndex, 6));
				tfOlusturmaTarihi.setText((String) model.getValueAt(selectedRowIndex, 7));

			}
		});
		
		
		
		
		
		
		tbTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Stok Kodu", "Stok Adı", "Stok Tipi",
				"Birimi", "Barkodu", "KDV Tipi", "Açıklama", "Oluşturma Tarihi" }));
		tbTable.setBounds(449, 227, 481, 176);
		scrollPane.setViewportView(tbTable);

		
		
		
		// KOPYALA

		JButton btnKopyala = new JButton("Kopyala");
		btnKopyala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String query = "insert into stokkart() values(?,?,?,?,?,?,?,?)";

				try {

					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setString(1, tfKopyala.getText());
					stmt.setString(2, tfStokAdı.getText());
					stmt.setObject(3, cbStokTipi.getSelectedItem());
					stmt.setObject(4, cbBirimi.getSelectedItem());
					stmt.setObject(5, tfBarkodu.getText());
					stmt.setObject(6, cbKdvTipi.getSelectedItem());
					stmt.setString(7, taAciklama.getText());
					stmt.setString(8, tfOlusturmaTarihi.getText());
					stmt.executeUpdate();
					stmt.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				TabloGuncelle.tabloGuncelle();

			}
		});

		btnKopyala.setBounds(252, 449, 89, 22);
		getContentPane().add(btnKopyala);

		tfKopyala = new JTextField();
		tfKopyala.setBounds(146, 450, 102, 22);
		getContentPane().add(tfKopyala);
		tfKopyala.setColumns(10);

		JLabel lblKopyala = new JLabel("Yeni stok kodunu girin:");
		lblKopyala.setBounds(10, 449, 138, 22);
		getContentPane().add(lblKopyala);

		JLabel lblArama = new JLabel("Aranacak stok kodunu girin:");
		lblArama.setBounds(378, 11, 165, 22);
		getContentPane().add(lblArama);

		
		
		
		
		//           ARAMA
		
		
		tfArama = new JTextField();
		tfArama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				

				ArrayList<StokModel> karts = getStokKarts();
				TabloGuncelle.tabloGuncellemeSearch(karts);

			}
		});

		
		
		
		tfArama.setBounds(553, 11, 263, 23);
		getContentPane().add(tfArama);
		tfArama.setColumns(10);

		
		
		
		// VERİLERİ LİSTELEME

		DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
		ArrayList<StokModel> liste2 = ListeleController.listele();
		for (StokModel i : liste2) {
			Object[] row = { i.getStokKodu(), i.getStokAdı(), i.getStokTipi(), i.getBirimi(), i.getBarkodu(),
					i.getKdvTipi(), i.getAcıklama(), i.getOlusturmaTarihi() };
			model.addRow(row);

		}

	}
	
	
	

	// TABLO GÜNCELLEME

	public static class TabloGuncelle {

		public static void tabloGuncelle() {

			DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
			model.setRowCount(0);
			ArrayList<StokModel> liste2 = ListeleController.listele();
			for (StokModel i : liste2) {
				Object[] row = { i.getStokKodu(), i.getStokAdı(), i.getStokTipi(), i.getBirimi(), i.getBarkodu(),
						i.getKdvTipi(), i.getAcıklama(), i.getOlusturmaTarihi() };
				model.addRow(row);

			}
		}
		
		
		
		

		public static void tabloGuncellemeSearch(ArrayList<StokModel> liste) {

			DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
			model.setRowCount(0);
			ArrayList<StokModel> liste2 = liste;
			for (StokModel i : liste2) {
				Object[] row = { i.getStokKodu(), i.getStokAdı(), i.getStokTipi(), i.getBirimi(), i.getBarkodu(),
						i.getKdvTipi(), i.getAcıklama(), i.getOlusturmaTarihi() };
				model.addRow(row);

			}

		}

	}
}

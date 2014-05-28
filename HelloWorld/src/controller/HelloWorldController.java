package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.JFileChooser;

import com.wia.util.IOUtil;

public class HelloWorldController {

	@FXML
	private Button ImportButton;

	@FXML
	private Button ExportButton;

	@FXML
	private Button EditButton;

	@FXML
	private Button SaveButton;

	@FXML
	private TextArea Catalog;

	@FXML
	private void initialize() {
		ImportButton.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {

				String path = read();
				try {
					ImportFile(path);
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		SaveButton.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				int i = 0;
				File file = new File("Catalog" + i + ".json");
				while (file.exists()) {
					// System.out.println("Hello");
					i++;
					file = new File("Catalog" + i + ".json");
				}
				FileWriter fw;
				try {
					fw = new FileWriter(file, true);
					fw.write(Catalog.getText());
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		EditButton.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				InputStream is = getClass().getResourceAsStream(
						"/DefaultCatalog.json");
				String str;
				try {
					str = IOUtil.parseInputStream(is);
					Catalog.setText(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		ExportButton.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {

				String path1 = read();
				try {
					ReadFile(path1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	@FXML
	public void handle() {

	}

	public static String read() {

		JFileChooser fd = new JFileChooser();
		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		String path = "";
		if (f != null) {
			path = f.getAbsolutePath();
		}
		return path;
	}

	public void ImportFile(String path2) throws IOException {

		File file = new File(path2);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

		BufferedReader reader = null;
		String str = "";
		try {
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				str = str + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		} // 保存到 bin/resource文件中

		String name = file.getName();
		String spath = new File("").getCanonicalPath() + "/src/resource" + "/"
				+ name;
		System.out.println(spath);
		FileWriter fw = new FileWriter(spath);
		PrintWriter out = new PrintWriter(fw);
		out.write(str);
		out.println();
		fw.close();
		out.close();

	}

	public void ReadFile(String path1) throws IOException {
		InputStream is = getClass().getResourceAsStream(
				"/resource/DefaultCatalog.json");
		String str = "";
		str = IOUtil.parseInputStream(is);

		FileWriter fw = new FileWriter(path1);
		PrintWriter out = new PrintWriter(fw);
		out.write(str);
		out.println();
		fw.close();
		out.close();
	}
}

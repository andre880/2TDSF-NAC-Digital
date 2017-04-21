package br.com.fiap.views;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.axis2.AxisFault;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.fiap.bo.JogoBOStub;
import br.com.fiap.bo.JogoBOStub.Cadastrar;
import br.com.fiap.bo.JogoBOStub.Jogo;

public class Janela {
	
	protected Shell shlWebservicesoaprequesterdesktop;
	private Text edtTitulo;
	private Text edtGenero;
	private Text edtDescricao;
	private Text edtCodigo;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Janela window = new Janela();			
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();		
		createContents();
		shlWebservicesoaprequesterdesktop.open();
		shlWebservicesoaprequesterdesktop.layout();
		while (!shlWebservicesoaprequesterdesktop.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWebservicesoaprequesterdesktop = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlWebservicesoaprequesterdesktop.setSize(488, 659);
		shlWebservicesoaprequesterdesktop.setText("WebserviceSOAP-requester-desktop");
		shlWebservicesoaprequesterdesktop.setLayout(new GridLayout(2, false));		
		
		Label lblCadastrar = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblCadastrar.setText("Cadastrar");
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Label label = new Label(shlWebservicesoaprequesterdesktop, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label lblTtulo = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblTtulo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTtulo.setText("T\u00EDtulo");
		
		edtTitulo = new Text(shlWebservicesoaprequesterdesktop, SWT.BORDER);
		edtTitulo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDataDeLanamento = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblDataDeLanamento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeLanamento.setText("Data de Lan\u00E7amento");
		
		DateTime dateTime = new DateTime(shlWebservicesoaprequesterdesktop, SWT.BORDER);
		
		Label lblGnero = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblGnero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGnero.setText("G\u00EAnero");
		
		edtGenero = new Text(shlWebservicesoaprequesterdesktop, SWT.BORDER);
		edtGenero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescrio = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descri\u00E7\u00E3o");
		
		edtDescricao = new Text(shlWebservicesoaprequesterdesktop, SWT.BORDER);
		edtDescricao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Button btnCadastrar = new Button(shlWebservicesoaprequesterdesktop, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				
				btnCadastrar.setEnabled(false);
				
				Display.getDefault().asyncExec(new Runnable() {
					
					@Override
					public void run() {
						
						try {
							
							
							String titulo = edtTitulo.getText();
							String genero = edtGenero.getText();
							String descricao = edtDescricao.getText();
							int dia = dateTime.getDay();
							int mes = dateTime.getMonth();
							int ano = dateTime.getYear();
							Calendar dataLancamento = new GregorianCalendar(ano, mes, dia);
							
							JogoBOStub stub = new JogoBOStub();
							Jogo jogo = new Jogo();
							jogo.setId(0);
							jogo.setTitulo(titulo);
							jogo.setGenero(genero);
							jogo.setDescricao(descricao);
							jogo.setDataLancamento(dataLancamento);
							Cadastrar cadastrar = new Cadastrar();
							cadastrar.setEntity(jogo);
							stub.cadastrar(cadastrar);
							
							mensagem("Cadastrar", "Cadastro realizado com sucesso!");
						} catch (AxisFault e1) {					
							e1.printStackTrace();
							mensagem("Erro", e1.getMessage());
						} catch (RemoteException e1) {					
							e1.printStackTrace();
							mensagem("Erro", e1.getMessage());
						}finally {
							btnCadastrar.setEnabled(true);
						}
						
					}
				});				
				
			}
		});
		btnCadastrar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnCadastrar.setText("Cadastrar");
		
		Label lblBuscar = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblBuscar.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblBuscar.setText("Buscar");
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Label label_1 = new Label(shlWebservicesoaprequesterdesktop, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label lblCdigoid = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblCdigoid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCdigoid.setText("C\u00F3digo(id)");
		
		edtCodigo = new Text(shlWebservicesoaprequesterdesktop, SWT.BORDER);
		edtCodigo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblTtulo_1 = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblTtulo_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTtulo_1.setText("T\u00EDtulo");
		
		Label lbTitulo = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lbTitulo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblDataDeLanamento_1 = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblDataDeLanamento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeLanamento_1.setText("Data de Lan\u00E7amento");
		
		Label lbDataLancamento = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lbDataLancamento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblGnero_1 = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblGnero_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGnero_1.setText("G\u00EAnero");
		
		Label lbGenero = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lbGenero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblDescrio_1 = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblDescrio_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio_1.setText("Descri\u00E7\u00E3o");
		
		Label lbDescricao = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lbDescricao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Button btnBuscar = new Button(shlWebservicesoaprequesterdesktop, SWT.NONE);
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnBuscar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnBuscar.setText("Buscar");
		
		Label lblListar = new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		lblListar.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblListar.setText("Listar");
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Label label_2 = new Label(shlWebservicesoaprequesterdesktop, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		table = new Table(shlWebservicesoaprequesterdesktop, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(44);
		tblclmnId.setText("Id");
		
		TableColumn tblclmnTtulo = new TableColumn(table, SWT.NONE);
		tblclmnTtulo.setWidth(90);
		tblclmnTtulo.setText("T\u00EDtulo");
		
		TableColumn tblclmnDataLanamento = new TableColumn(table, SWT.NONE);
		tblclmnDataLanamento.setWidth(124);
		tblclmnDataLanamento.setText("Data Lan\u00E7amento");
		
		TableColumn tblclmnGnero = new TableColumn(table, SWT.NONE);
		tblclmnGnero.setWidth(100);
		tblclmnGnero.setText("G\u00EAnero");
		
		TableColumn tblclmnDescrio = new TableColumn(table, SWT.NONE);
		tblclmnDescrio.setWidth(100);
		tblclmnDescrio.setText("Descri\u00E7\u00E3o");
		new Label(shlWebservicesoaprequesterdesktop, SWT.NONE);
		
		Button btnListar = new Button(shlWebservicesoaprequesterdesktop, SWT.NONE);
		btnListar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnListar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnListar.setText("Listar");

	}
	
	private void mensagem(String title, String msg){
		MessageBox mBox = new MessageBox(shlWebservicesoaprequesterdesktop, SWT.OK);
		mBox.setText(title);
		mBox.setMessage(msg);
		mBox.open();
	}
	
}

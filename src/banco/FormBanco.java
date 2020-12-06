/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class FormBanco extends javax.swing.JFrame {
     public ArrayList<Cliente> clientes = new ArrayList();
     private ArrayList<ContaCorrente>ListcontaCor= new ArrayList();
     private ArrayList<ContaEspecial>ListcontaEsp = new ArrayList();
     public ArrayList<Cidade> cidade =  new ArrayList();
    
     Movimentacao movimentacao;
     ContaCorrente contaCorrente;
     ContaEspecial contaEspecial;
     

    /**
     * Creates new form FormBanco
     */
    public FormBanco() {
        initComponents();
        diretoNoCodigo();
        preencheCombo();
        preencheComboContaC();
        preencheComboContaE();
        
        
    }
    public void  preencheCombo(){
        cbCliente.removeAllItems();
        for (Cliente c : clientes){
            cbCliente.addItem(c.getNome());
        }
    }
    
    public void  preencheComboContaC(){
        cbClienteC.removeAllItems();
        for (ContaCorrente c : ListcontaCor){
            for( Cliente cli: c.getClientes())
            cbClienteC.addItem(cli.getNome());
        }
    }
    
    public void  preencheComboContaE(){
        cbClienteE.removeAllItems();
        for (ContaEspecial c : ListcontaEsp){
            for(Cliente cli: c.getClientes())
            cbClienteE.addItem(cli.getNome());
        }
    }
    
    public void diretoNoCodigo(){
        cidade.add(new Cidade(1,"Palmas", "TO"));
        cidade.add(new Cidade(2,"Araguaina", "TO"));
        cidade.add(new Cidade(3,"Divinopolis", "TO"));
        
        clientes.add(new Cliente(444, "Tesla ", "25/09/2012", "(62) 96345-6666", cidade.get(1)));
        clientes.add(new Cliente(2, "jose", "10-10-1997", "99999998", cidade.get(1)));
        clientes.add(new Cliente(3, "maria", "10-10-1998", "99999997", cidade.get(2)));
        clientes.add(new Cliente(4, "eu", "10-10-1999", "99999996", cidade.get(2)));
        clientes.add(new Cliente(5, "tu", "10-10-2000", "99999995", cidade.get(0)));
        clientes.add(new Cliente(6, "eles", "10-10-2001", "99999994",cidade.get(2)));
        

        
        ListcontaCor.add(new ContaCorrente(100, "20/09/2019" , 20));
        ListcontaCor.get(0).insereCliente(clientes.get(0));
        ListcontaCor.get(0).insereCliente(clientes.get(1));
        ListcontaCor.add(new ContaCorrente(100, "01/12/2019" , 30));
        ListcontaCor.get(1).insereCliente(clientes.get(2));
        ListcontaCor.add(new ContaCorrente(100, "28/05/2019" , 40));  
        ListcontaCor.get(2).insereCliente(clientes.get(3));
        
        ListcontaEsp.add(new ContaEspecial(100, "10/04/2020", 10, 200));
        ListcontaEsp.get(0).insereCliente(clientes.get(4));
        ListcontaEsp.add(new ContaEspecial(100, "11/01/2020", 04, 200));
        ListcontaEsp.get(1).insereCliente(clientes.get(5));
    }    

    public Cliente existeCliente(String nome){
        for (Cliente c : clientes){
            if (c.getNome().equals(nome)){
                return c;
            }
        }        
        return null;
    }
    
    public void pesquisar() {
        int numero = cbCliente.getSelectedIndex();

        for (ContaCorrente cc : ListcontaCor) {
            String nome = clientes.get(numero).getNome();
            for (Cliente cli : cc.getClientes()) {
                if (nome == cli.getNome()) {
                    taSaida.append( "Data Abertura: " + cc.getDataAbertura()+ " \n" + "Codigo da conta: " + cc.getCodConta() + " \n" + "Informações do cliente: " + clientes.get(numero).toString());
                }
            }
        }
         for (ContaEspecial cc : ListcontaEsp) {
            String nome = clientes.get(numero).getNome();
            for (Cliente cli : cc.getClientes()) {
                if (nome == cli.getNome()) {
                    taSaida.append( "Data Abertura: " + cc.getDataAbertura()+ " \n" + "Codigo da conta: " + cc.getCodConta() + " \n" + "Informações do cliente: " + clientes.get(numero).toString());
                }
            }
        }
        
    }
    
    public void Sacar() {
        String valor="Valor sacado: " ;
        double numero = Double.parseDouble(tfValor.getText());
        int id = cbCliente.getSelectedIndex();
 
        if (rbCorrente.isSelected()) {
            for (ContaCorrente cc : ListcontaCor) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()){
                        cc.sacar(numero);
                        for(Movimentacao m: cc.getListaMov()){
                            taSaida.setText(valor+="\n"+ "Valor depositado: " + m.getValorMov() + "\n"+ "Data e Hora: " + m.getData() + " " + m.getHorario() + "\n" + "Saldo Anterior: "+ m.getSaldoAnt() + "\n" + "Saldo Atual: "+ m.getSaldoDep());
                        }
                    }
                    
                }
            }
        }
        if (rbEspecial.isSelected()){
            for (ContaEspecial cc : ListcontaEsp) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()){
                        cc.sacar(numero);
                        for(Movimentacao m: cc.getListaMov()){
                            taSaida.setText(valor+="\n"+ "Valor depositado: " + m.getValorMov() + "\n"+ "Data e Hora: " + m.getData() + " " + m.getHorario() + "\n" + "Saldo Anterior: "+ m.getSaldoAnt() + "\n" + "Saldo Atual: "+ m.getSaldoDep());
                        }
                    }  
                }
            }
        }
    }
    
    public void Depositar() {
        String valor = "Dados do Deposito: ";
        int id = cbCliente.getSelectedIndex();
        double numero = Double.parseDouble(tfValor.getText());
        
        if (rbCorrente.isSelected()) {
            for (ContaCorrente cc : ListcontaCor) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()){
                        cc.depositar(numero);
                        for(Movimentacao m: cc.getListaMov()){
                            taSaida.setText(valor+="\n"+ "Valor depositado: " + m.getValorMov() + "\n"+ "Data e Hora: " + m.getData() + " " + m.getHorario() + "\n" + "Saldo Anterior: "+ m.getSaldoAnt() + "\n" + "Saldo Atual: "+ m.getSaldoDep());
                        }
                    }
                }
            }
        }
         if (rbEspecial.isSelected()) {
            for (ContaEspecial cc : ListcontaEsp) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()){
                        cc.depositar(numero);
                        for(Movimentacao m: cc.getListaMov()){
                            taSaida.setText(valor+="\n"+ "Valor depositado: " + m.getValorMov() + "\n"+ "Data e Hora: " + m.getData() + " " + m.getHorario() + "\n" + "Saldo Anterior: "+ m.getSaldoAnt() + "\n" + "Saldo Atual: "+ m.getSaldoDep());
                        }
                    }
                }
            }
        }
    }
    
    public void Transfirir() {
        String valor = "Dados do Deposito: ";
        int id = cbCliente.getSelectedIndex();
        double numero = Double.parseDouble(tfValor.getText());

        int destinoCor = cbClienteC.getSelectedIndex();
        int destinoEsp = cbClienteE.getSelectedIndex();

        if (rbCorrente.isSelected()) {
            for (ContaCorrente cc : ListcontaCor) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()) {
                        contaCorrente = ListcontaCor.get(destinoCor);
                            cc.transferir(contaCorrente, numero);
                            for (Movimentacao m : cc.getListaMov()) {
                                for (Movimentacao outrac : contaCorrente.getListaMov()) {
                                    taSaida.setText("\n Dados e Valor da transferencia: " + "\n" + m.toString() + "\n Conta da transferencia enviada: " + "\n" + outrac.toString());
                                }
                            
                        }
                    }
                }
            }
        }
        if (rbEspecial.isSelected()) {
            for (ContaEspecial cc : ListcontaEsp) {
                String nome = clientes.get(id).getNome();
                for (Cliente cli : cc.getClientes()) {
                    if (nome == cli.getNome()) {
                        contaEspecial = ListcontaEsp.get(destinoEsp);
                        cc.transferir(contaEspecial, numero);
                        for (Movimentacao m : cc.getListaMov()) {
                            for (Movimentacao outrac : contaEspecial.getListaMov()) {
                                taSaida.setText("\n Dados e Valor da transferencia: " + "\n" + m.toString() + "\n Conta da transferencia enviada: " + "\n" + outrac.toString());
                            }
                        }
                    }
                }
            }
        }
    }
         
    
    public void Extrato(){
        int numero = cbCliente.getSelectedIndex();
        
        for (ContaCorrente cc : ListcontaCor) {
            String nome = clientes.get(numero).getNome();
            for (Cliente cli : cc.getClientes()) {
                if (nome == cli.getNome()) {
                    taSaida.append("EXTRATO" + "\n"+ "Codigo da conta: " + cc.getCodConta() + " \n" + "Data Abertura: " + cc.getDataAbertura() 
                            + " \n" + "Saldo: " + cc.getSaldo() + " \n" + "Data: " + cc.getData() + "\n" + "Hora: " 
                            + cc.getHora() + "Informações do cliente: " + clientes.get(numero).toString()+
                            "\n" + "Movimentação: " + cc.getListaMov());
                }
            }
        }
         for (ContaEspecial cc : ListcontaEsp) {
            String nome = clientes.get(numero).getNome();
            for (Cliente cli : cc.getClientes()) {
                if (nome == cli.getNome()) {
                     taSaida.append("EXTRATO" + "\n"+ "Codigo da conta: " + cc.getCodConta() + " \n" + "Data Abertura: " + cc.getDataAbertura() 
                            + " \n" + "Saldo: " + cc.getSaldo() + " \n" + "Data: " + cc.getData() + "\n" + "Hora: " 
                            + cc.getHora() + "Informações do cliente: " + clientes.get(numero).toString() +
                            "\n" + "Movimentação: " + cc.getListaMov());
                }
            }
        }
        
    }
 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jbLocarlizar = new javax.swing.JButton();
        jbSacar = new javax.swing.JButton();
        jbDepositar = new javax.swing.JButton();
        jbTransferir = new javax.swing.JButton();
        tfValor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taSaida = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        rbCorrente = new javax.swing.JRadioButton();
        rbEspecial = new javax.swing.JRadioButton();
        cbClienteC = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbClienteE = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cliente:");

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });

        jbLocarlizar.setText("Localizar");
        jbLocarlizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocarlizarActionPerformed(evt);
            }
        });

        jbSacar.setText("Sacar");
        jbSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSacarActionPerformed(evt);
            }
        });

        jbDepositar.setText("Depositar");
        jbDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDepositarActionPerformed(evt);
            }
        });

        jbTransferir.setText("Transferência");
        jbTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTransferirActionPerformed(evt);
            }
        });

        tfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorActionPerformed(evt);
            }
        });

        jLabel2.setText("Digite um valor:");

        jLabel3.setText("Tipo de Conta:");

        jLabel4.setText("Conta Corrente:");

        taSaida.setColumns(20);
        taSaida.setRows(5);
        jScrollPane1.setViewportView(taSaida);

        jToggleButton1.setText("Ver Extrato");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbCorrente);
        rbCorrente.setText("Corrente");

        buttonGroup1.add(rbEspecial);
        rbEspecial.setText("Especial");

        cbClienteC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbClienteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteCActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo da Conta de transferencia:");

        jLabel6.setText("Conta Especial:");

        cbClienteE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbClienteE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(rbCorrente)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbEspecial))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbClienteC, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbClienteE, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbLocarlizar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jbLocarlizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbCorrente)
                            .addComponent(rbEspecial))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbClienteC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbClienteE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbDepositar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSacar)
                        .addGap(30, 30, 30)
                        .addComponent(jbTransferir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbLocarlizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocarlizarActionPerformed
        taSaida.setText(null);
        pesquisar();
    }//GEN-LAST:event_jbLocarlizarActionPerformed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbClienteActionPerformed

    private void tfValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorActionPerformed

    private void jbSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSacarActionPerformed
        taSaida.setText(null);
        Sacar();
    }//GEN-LAST:event_jbSacarActionPerformed

    private void jbDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDepositarActionPerformed
        taSaida.setText(null);
        Depositar();
    }//GEN-LAST:event_jbDepositarActionPerformed

    private void jbTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTransferirActionPerformed
        taSaida.setText(null);
        Transfirir();
    }//GEN-LAST:event_jbTransferirActionPerformed

    private void cbClienteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbClienteCActionPerformed

    private void cbClienteEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbClienteEActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        taSaida.setText(null);
        Extrato();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBanco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBanco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbClienteC;
    private javax.swing.JComboBox<String> cbClienteE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton jbDepositar;
    private javax.swing.JButton jbLocarlizar;
    private javax.swing.JButton jbSacar;
    private javax.swing.JButton jbTransferir;
    private javax.swing.JRadioButton rbCorrente;
    private javax.swing.JRadioButton rbEspecial;
    private javax.swing.JTextArea taSaida;
    private javax.swing.JTextField tfValor;
    // End of variables declaration//GEN-END:variables
}

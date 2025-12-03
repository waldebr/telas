package sistema;

import Conexao.Conexao;
import dao.ItemDAO;
import dao.PedidoDAO; // Necessário para a função de fazer o pedido
import model.Pedido;   // Necessário para a função de fazer o pedido
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableCellRenderer;
import java.io.File; // IMPORT NECESSÁRIO
import java.util.UUID; // IMPORT NECESSÁRIO


public class Cardapio extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private Carrinho carrinho;
    private List<Item> listaItens;
    private String uuidCliente;
    
    
    /**
     * Creates new form Cardapio
     */
    public Cardapio() {        
        // Geração de UUID correta
        this.uuidCliente = UUID.randomUUID().toString();
        System.out.println("UUID da sessão do cliente: " + uuidCliente);
        
        initComponents();
 
        tableModel = (DefaultTableModel) tblCardapio.getModel();
        carrinho = new Carrinho();
        this.listaItens = new ArrayList<>();
        
        // Carrega o cardapio ao inicializar
        carregarCardapio();
        
        // Personaliza a tabela (configura o renderizador de imagem)
        personalizarTabela();
        
        btnAdicionar.addActionListener(e -> adicionarAoCarrinho());
        btnVerCarrinho.addActionListener(e -> abrirTelaCarrinho());

        // Modificando a chamada da TelaPedidos para ser visível
        btnMeusPedidos.addActionListener(e -> {
            new TelaPedidos(this, uuidCliente).setVisible(true); // Assume que TelaPedidos existe
        });
    }
    
    // MÉTODO IMPLEMENTADO: Popula a tabela com os dados de this.listaItens
    private void popularTabela() {
        tableModel.setRowCount(0); // Limpa a tabela
        
        if (this.listaItens != null) {
            for (Item item : this.listaItens) {
                // Formata o preço para exibição
                String precoFormatado = String.format("R$ %.2f", item.getPreco());
                
                Object[] linha = {
                    item.getId(),
                    item.getNome(),
                    precoFormatado,
                    item.getImagem() // Caminho da imagem
                };
                tableModel.addRow(linha);
            }
            
            // Oculta a coluna ID
            tblCardapio.getColumnModel().getColumn(0).setMinWidth(0);
            tblCardapio.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCardapio.getColumnModel().getColumn(0).setWidth(0);
            
            // Seleciona o primeiro item, se houver
            if (tableModel.getRowCount() > 0) {
                tblCardapio.setRowSelectionInterval(0, 0);
            }
        }
    }
    
    private void personalizarTabela() {
        tblCardapio.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);

                if (value != null) {
                    // O valor da célula é o caminho da imagem (string)
                    String caminho = value.toString().replace("\\", File.separator); 
                    File arquivoImagem = new File(caminho);

                    if (arquivoImagem.exists()) {
                        try {
                            ImageIcon imagem = new ImageIcon(arquivoImagem.getAbsolutePath());

                            // Pega tamanho da célula
                            int largura = table.getColumnModel().getColumn(column).getWidth();
                            int altura = table.getRowHeight(row);

                            // Redimensiona a imagem proporcionalmente
                            Image imgRedimensionada = imagem.getImage().getScaledInstance(
                                    largura - 10, altura - 5, Image.SCALE_SMOOTH);

                            label.setIcon(new ImageIcon(imgRedimensionada));
                            label.setText("");
                        } catch (Exception e) {
                            label.setText("Erro");
                        }
                    } else {
                        label.setText("N/A");
                    }
                } else {
                    label.setText("N/A");
                }

                return label;
            }
        });

        // Aumenta a altura das linhas para caber melhor as imagens
        tblCardapio.setRowHeight(60);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCardapio = new javax.swing.JLabel();
        painelControles = new javax.swing.JPanel();
        lblQuantidade = new javax.swing.JLabel();
        spinnerQuantidade = new javax.swing.JSpinner();
        btnAdicionar = new javax.swing.JButton();
        btnVerCarrinho = new javax.swing.JButton();
        btnMeusPedidos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCardapio = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cardapio");
        setLocation(new java.awt.Point(0, 0));

        lblCardapio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCardapio.setText("Cardápio");

        painelControles.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblQuantidade.setText("Quantidade:");

        spinnerQuantidade.setName(""); // NOI18N

        btnAdicionar.setText("Adicionar ao Carrinho");

        btnVerCarrinho.setText("Ver Carrinho");

        btnMeusPedidos.setText("Meus Pedidos");
        btnMeusPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusPedidosActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelControlesLayout = new javax.swing.GroupLayout(painelControles);
        painelControles.setLayout(painelControlesLayout);
        painelControlesLayout.setHorizontalGroup(
            painelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelControlesLayout.createSequentialGroup()
                        .addComponent(lblQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMeusPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelControlesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(93, 93, 93))
        );
        painelControlesLayout.setVerticalGroup(
            painelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidade)
                    .addComponent(spinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(btnVerCarrinho)
                .addGap(18, 18, 18)
                .addComponent(btnMeusPedidos)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        btnSair.setText("jButton1");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        tblCardapio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "nome", "preco", "imagem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCardapio.setColumnSelectionAllowed(true);
        tblCardapio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCardapioKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblCardapio);
        tblCardapio.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(painelControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(lblCardapio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCardapio)
                    .addComponent(btnSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMeusPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeusPedidosActionPerformed

        
    }//GEN-LAST:event_btnMeusPedidosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        carregarCardapio();
    JOptionPane.showMessageDialog(this, "Cardápio atualizado com sucesso!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblCardapioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCardapioKeyPressed

    }//GEN-LAST:event_tblCardapioKeyPressed

public void carregarCardapio() { 
    // 1. Cria uma instância do DAO
    ItemDAO itemDAO = new ItemDAO();
    
    // 2. Chama o método que consulta o banco diretamente
    this.listaItens = itemDAO.listarCardapioDisponivel();
    
    // 3. Verifica e popula a tabela com os itens
    if (this.listaItens != null && !this.listaItens.isEmpty()) {
        popularTabela(); 
        personalizarTabela(); // Garante que a tabela seja renderizada após popular
    } else {
        tableModel.setRowCount(0); // Limpa a tabela se não houver itens
        JOptionPane.showMessageDialog(this, "Não foi possível carregar o cardápio ou ele está vazio.", 
                                      "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
    // Método para adicionar item ao carrinho
    private void adicionarAoCarrinho() {
        int linhaSelecionada = tblCardapio.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item do cardápio!");
            return;
        }
        
        // Pegar o ID do item selecionado na tabela (coluna 0, que está oculta)
        int id = (int) tableModel.getValueAt(linhaSelecionada, 0); 
        
        // Obter a quantidade selecionada, garantindo que seja um valor válido (> 0)
        int quantidade = (int) spinnerQuantidade.getValue();
        if (quantidade <= 0) {
            JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!");
            return;
        }
        
        // Buscar o item na lista de itens (que contém o objeto Item completo)
        Item itemSelecionado = null;
        for (Item item : listaItens) {
            if (item.getId() == id) {
                itemSelecionado = item;
                break;
            }
        }
        
        if (itemSelecionado != null) {
            // Adicionar o item ao carrinho conforme a quantidade
            for (int i = 0; i < quantidade; i++) {
                carrinho.adicionarItem(itemSelecionado);
            }
            
            JOptionPane.showMessageDialog(this, quantidade + " x " + itemSelecionado.getNome() + " adicionado ao carrinho!");
        } else {
             JOptionPane.showMessageDialog(this, "Erro: Item não encontrado na lista interna.");
        }
    }

    private void finalizarPedido() {
    // 1. Verifica se o carrinho está vazio
    if (carrinho.carrinhoVazio()) {
        JOptionPane.showMessageDialog(this, "Seu carrinho está vazio!");
        return;
    }

    // 2. Cria o objeto Pedido e o associa ao carrinho local
    Pedido pedido = new Pedido();
    pedido.setCarrinho(carrinho);
    pedido.setValorTotal(carrinho.calcularTotal());
    // Mantém o UUID para rastrear pedidos
    pedido.setUuidCliente(this.uuidCliente); 
    pedido.setStatus("Em andamento"); // Define o status inicial

    // 3. Salva no banco de dados usando o DAO
    PedidoDAO pedidoDAO = new PedidoDAO();
    boolean salvou = pedidoDAO.inserir(pedido);

    // 4. Feedback e limpeza
    if (salvou) {
        JOptionPane.showMessageDialog(this, "Pedido realizado com sucesso!\nTotal: R$ " + String.format("%.2f", pedido.getValorTotal()), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        carrinho = new Carrinho(); // Limpa o carrinho
        // Opcional: Atualizar a exibição do carrinho
    } else {
        JOptionPane.showMessageDialog(this, "Erro ao registrar o pedido. Verifique a conexão com o BD.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void abrirTelaCarrinho() {
        // Implemente aqui a chamada para sua TelaCarrinho
        JOptionPane.showMessageDialog(this, "Funcionalidade Ver Carrinho deve ser implementada abrindo a TelaCarrinho.");
    }
    
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
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

  
        // ... (código try-catch omitido por brevidade)
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cardapio form = new Cardapio();
                form.setVisible(true);
                
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnMeusPedidos;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVerCarrinho;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCardapio;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JPanel painelControles;
    private javax.swing.JSpinner spinnerQuantidade;
    private javax.swing.JTable tblCardapio;
    // End of variables declaration//GEN-END:variables
}

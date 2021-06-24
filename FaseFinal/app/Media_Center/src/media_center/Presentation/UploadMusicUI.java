/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Presentation;

import media_center.Business.Music;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import media_center.Business.UserOwnsMusic;
import media_center.DAOs.MusicDAO;
import media_center.DAOs.UserOwnsMusicDAO;

/**
 *
 * @author pacifico
 */
public class UploadMusicUI extends javax.swing.JFrame {
    private UploadMediaUI user = null;
    String path = null;
    String file_name = null;
    
    /**
     * Creates new form UploadMusicUI
     * @param u
     */
    public UploadMusicUI(UploadMediaUI u) {
        user = u;
        initComponents();
        jLabel1.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upload Music", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Vera Sans", 1, 24))); // NOI18N

        jTextField1.setBackground(new java.awt.Color(204, 255, 255));
        jTextField1.setText("Insert title here");
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Title"));

        jTextField2.setBackground(new java.awt.Color(204, 255, 255));
        jTextField2.setText("Insert album title here");
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Album Title"));

        jTextField3.setBackground(new java.awt.Color(204, 255, 255));
        jTextField3.setText("Insert artist/band here");
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("Artist/Band"));

        jTextField4.setBackground(new java.awt.Color(204, 255, 255));
        jTextField4.setText("Insert release date here (year)");
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder("Release Date"));

        jTextField5.setBackground(new java.awt.Color(204, 255, 255));
        jTextField5.setText("Insert Genre here");
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder("Genre"));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("Path to file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 204, 255));
        jButton3.setText("Upload");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField6.setBackground(new java.awt.Color(204, 255, 255));
        jTextField6.setText("Insert number in album here");
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder("Number in Album"));

        jLabel1.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 12)); // NOI18N
        jLabel1.setText("Done");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BACK BUTTON
        this.dispose();
        user.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // UPLOAD BUTTON       
        String title = jTextField1.getText();
        String album_title = jTextField2.getText();
        String artist = jTextField3.getText();
        int release_date;
        try {
            release_date = Integer.parseInt(jTextField4.getText());
        } catch (NumberFormatException nfe) {
            release_date = -1;
        }
        String genre = jTextField5.getText();
        int nr;
        try {
            nr = Integer.parseInt(jTextField6.getText());
        } catch (NumberFormatException nfe) {
            nr = -1;
        }
        
        if(release_date > 0 && nr > 0
           && !title.isEmpty()
           && !album_title.isEmpty()
           && !artist.isEmpty()
           && !genre.isEmpty()
           && !path.isEmpty())
        {
            MusicDAO mdao = new MusicDAO();
            UserOwnsMusicDAO umdao = new UserOwnsMusicDAO();
           
            int id = mdao.size();
            String new_path = "Media/Music/" + file_name;
            Music music = new Music(id, title, album_title, artist, new_path, release_date, genre, nr);
            UserOwnsMusic um = new UserOwnsMusic(id, user.getUser().getUsername());
            
            int old_id;
            if((old_id = mdao.checkDuplicate(music)) != -1){
                System.out.println("Found duplicate.");
                um.setMusic_id(old_id);
                umdao.add(um);
                JOptionPane.showMessageDialog(this, "Duplicate Music found, you now have ownership of that Music.");
            }else{
                System.out.println("No duplicate.");
                ProcessBuilder pbb = new ProcessBuilder("cp", "-a", path, "Media/Music/");
                try {
                    pbb.start();
                } catch (IOException ex) {
                    Logger.getLogger(LoggedInUI.class.getName()).log(Level.SEVERE, null, ex);
                }   

                mdao.put(id, music);
                umdao.add(um);
                JOptionPane.showMessageDialog(this, "Successfully uploaded music file.");
            }
            System.out.println(music.toString()+" and "+um.toString());
        }else{
            JOptionPane.showMessageDialog(this, "Invalid data.");     
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // PATH TO FILE BUTTON
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a music file.");
        jfc.setAcceptAllFileFilterUsed(false);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Music files", "mp3", "wav", "flac", "m4a", "ogg");
	jfc.addChoosableFileFilter(filter);

	int returnValue = jfc.showSaveDialog(null);
        

	if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String command;
            System.out.println("File selected: \""+selectedFile.getAbsolutePath() +"\"");
            command = selectedFile.getAbsolutePath();
            path = command;
            file_name = selectedFile.getName();
            jLabel1.setVisible(true);
	}
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
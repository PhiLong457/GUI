package MathQuizGame; // Định nghĩa package chứa chương trình, giúp tổ chức mã nguồn theo module.

import java.awt.*; // Import thư viện AWT để làm giao diện đồ họa.
import java.awt.event.*; // Import các sự kiện để xử lý hành động.
import javax.swing.*; // Import thư viện Swing để tạo giao diện đồ họa.
import java.util.Random; // Import lớp Random để tạo số ngẫu nhiên.

public final class MathQuizGame implements ActionListener { // Lớp chính, sử dụng giao diện ActionListener để lắng nghe sự kiện.

    JFrame frame; // Khung chính của giao diện.
    JLabel questionLabel, scoreLabel; // Nhãn hiển thị câu hỏi và điểm số.
    JTextField answerField; // Ô nhập liệu để người dùng nhập câu trả lời.
    JButton submitButton, nextButton, exitButton; // Các nút: Gửi, Tiếp theo, Thoát.
    int correctAnswer, score = 0, questionCount = 0, maxQuestions = 10; // Biến lưu câu trả lời đúng, điểm số, số câu đã hỏi và số câu tối đa.
    Random random; // Đối tượng Random để tạo số ngẫu nhiên.

    MathQuizGame() { // Constructor khởi tạo đối tượng trò chơi.
        random = new Random(); // Tạo đối tượng Random.
        prepareGUI(); // Gọi phương thức tạo giao diện.
        generateQuestion(); // Gọi phương thức tạo câu hỏi đầu tiên.
    }

    public void prepareGUI() { // Phương thức thiết kế giao diện.
        frame = new JFrame("Math Quiz Game"); // Tạo cửa sổ trò chơi với tiêu đề.
        frame.setSize(400, 300); // Đặt kích thước cửa sổ.
        frame.setLayout(null); // Tắt bố cục mặc định để tự đặt vị trí các thành phần.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi nhấn "X".
        frame.setLocationRelativeTo(null); // Căn giữa cửa sổ trên màn hình.

        questionLabel = new JLabel("Question: "); // Tạo nhãn hiển thị câu hỏi.
        questionLabel.setBounds(50, 50, 300, 30); // Đặt vị trí và kích thước nhãn.
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Đặt font chữ cho nhãn.
        frame.add(questionLabel); // Thêm nhãn vào khung.

        scoreLabel = new JLabel("Score: 0"); // Tạo nhãn hiển thị điểm số.
        scoreLabel.setBounds(50, 20, 150, 20); // Đặt vị trí và kích thước nhãn.
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Đặt font chữ cho nhãn.
        frame.add(scoreLabel); // Thêm nhãn vào khung.

        answerField = new JTextField(); // Tạo ô nhập liệu cho câu trả lời.
        answerField.setBounds(50, 100, 150, 30); // Đặt vị trí và kích thước ô nhập liệu.
        frame.add(answerField); // Thêm ô nhập liệu vào khung.

        submitButton = new JButton("Submit"); // Tạo nút "Submit".
        submitButton.setBounds(220, 100, 100, 30); // Đặt vị trí và kích thước nút.
        submitButton.addActionListener(this); // Gắn sự kiện khi nhấn nút.
        frame.add(submitButton); // Thêm nút vào khung.

        nextButton = new JButton("Next"); // Tạo nút "Next".
        nextButton.setBounds(50, 150, 100, 30); // Đặt vị trí và kích thước nút.
        nextButton.addActionListener(this); // Gắn sự kiện khi nhấn nút.
        frame.add(nextButton); // Thêm nút vào khung.

        exitButton = new JButton("Exit"); // Tạo nút "Exit".
        exitButton.setBounds(220, 150, 100, 30); // Đặt vị trí và kích thước nút.
        exitButton.addActionListener(this); // Gắn sự kiện khi nhấn nút.
        frame.add(exitButton); // Thêm nút vào khung.

        frame.setVisible(true); // Hiển thị cửa sổ giao diện.
    }

    public void generateQuestion() { // Phương thức tạo câu hỏi mới.
        int num1 = random.nextInt(10) + 1; // Số ngẫu nhiên từ 1 đến 10.
        int num2 = random.nextInt(10) + 1; 
        int operation = random.nextInt(4); // Chọn phép toán ngẫu nhiên: +, -, *, /.

        switch (operation) { // Xử lý từng loại phép toán.
            case 0 -> { // Phép cộng.
                questionLabel.setText("Question: " + num1 + " + " + num2 + " = ?");
                correctAnswer = num1 + num2; // Lưu kết quả đúng.
            }
            case 1 -> { // Phép trừ.
                questionLabel.setText("Question: " + num1 + " - " + num2 + " = ?");
                correctAnswer = num1 - num2;
            }
            case 2 -> { // Phép nhân.
                questionLabel.setText("Question: " + num1 + " * " + num2 + " = ?");
                correctAnswer = num1 * num2;
            }
            case 3 -> { // Phép chia.
                questionLabel.setText("Question: " + num1 + " / " + num2 + " = ?");
                correctAnswer = num1 / num2; // Lưu kết quả phép chia nguyên.
            }
        }

        questionCount++; // Tăng số câu đã hỏi.
        answerField.setText(""); // Xóa nội dung trong ô nhập liệu.
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Xử lý sự kiện khi nhấn nút.
        if (e.getSource() == submitButton) { // Khi nhấn nút "Submit".
            try {
                int playerAnswer = Integer.parseInt(answerField.getText()); // Lấy câu trả lời từ ô nhập liệu.
                if (playerAnswer == correctAnswer) { // So sánh với đáp án đúng.
                    JOptionPane.showMessageDialog(frame, "Correct Answer!"); // Hiển thị thông báo đúng.
                    score++; // Tăng điểm số.
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong Answer! Correct was: " + correctAnswer); // Thông báo sai.
                }
                scoreLabel.setText("Score: " + score); // Cập nhật điểm số trên giao diện.
            } catch (NumberFormatException ex) { // Xử lý khi người dùng nhập sai định dạng.
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!"); // Thông báo nhập sai.
            }
        } else if (e.getSource() == nextButton) { // Khi nhấn nút "Next".
            if (questionCount >= maxQuestions) { // Nếu đã hỏi đủ câu hỏi.
                JOptionPane.showMessageDialog(frame, "Game Over! Your score is: " + score); // Hiển thị kết quả cuối.
                frame.dispose(); // Đóng giao diện.
            } else {
                generateQuestion(); // Tạo câu hỏi mới.
            }
        } else if (e.getSource() == exitButton) { // Khi nhấn nút "Exit".
            frame.dispose(); // Đóng giao diện.
        }
    }

    public static void main(String[] args) { // Phương thức chính để chạy chương trình.
        MathQuizGame mathQuizGame = new MathQuizGame(); // Tạo đối tượng trò chơi.
    }
}

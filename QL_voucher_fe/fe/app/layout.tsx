import Sidebar from './components/Sidebar';
import './globals.css';

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className="bg-gray-50">
        <div className="flex">
          {/* Sidebar cố định bên trái */}
          <Sidebar />
          
          {/* Nội dung trang bên phải (cách lề 64 tương ứng độ rộng sidebar) */}
          <main className="flex-1 ml-64 min-h-screen">
            {children}
          </main>
        </div>
      </body>
    </html>
  );
}
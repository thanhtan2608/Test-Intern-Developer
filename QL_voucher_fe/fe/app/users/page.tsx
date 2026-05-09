"use client";

import React, { useEffect, useState } from 'react';
import { Plus, Edit2, Trash2, User, Mail, Phone, X, Loader2, Search, TicketCheck } from 'lucide-react';
import { UsersResponse, UsersRequest } from '@/modules/users/types/users.type';
import { getAllUsersUseCase } from '@/modules/users/useCases/getAllUsersUseCase';
import { createUsersUseCase } from '@/modules/users/useCases/createUsersUseCase';
// Import thêm UseCase sử dụng Voucher
import { useVoucherUseCase } from '@/modules/voucher_usages/useCases/useVoucherUseCase';

export default function UserPage() {
  const [users, setUsers] = useState<UsersResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isUseVoucherModalOpen, setIsUseVoucherModalOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  
  // Trạng thái cho việc sử dụng voucher
  const [selectedUser, setSelectedUser] = useState<UsersResponse | null>(null);
  const [voucherCode, setVoucherCode] = useState('');

  const [formData, setFormData] = useState<UsersRequest>({
    name: '',
    email: '',
    phone: ''
  });

  const fetchUsers = async () => {
    try {
      const data = await getAllUsersUseCase();
      setUsers(data || []);
    } catch (error) {
      console.error("Lỗi khi lấy danh sách user:", error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await createUsersUseCase(formData);
      closeModal();
      await fetchUsers();
    } catch (error) {
      alert("Có lỗi xảy ra khi tạo người dùng!");
    } finally {
      setIsLoading(false);
    }
  };

  // Xử lý nộp mã voucher
  const handleUseVoucher = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!selectedUser || !voucherCode) return;
    
    setIsLoading(true);
    try {
      await useVoucherUseCase({
        userId: selectedUser.id,
        voucherCode: voucherCode.toUpperCase()
      });
      alert(`Sử dụng voucher ${voucherCode} thành công cho ${selectedUser.name}!`);
      closeUseVoucherModal();
    } catch (error: any) {
      alert(error.message || "Lỗi: Voucher hết hạn hoặc không tồn tại!");
    } finally {
      setIsLoading(false);
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setFormData({ name: '', email: '', phone: '' });
  };

  const openUseVoucherModal = (user: UsersResponse) => {
    setSelectedUser(user);
    setIsUseVoucherModalOpen(true);
  };

  const closeUseVoucherModal = () => {
    setIsUseVoucherModalOpen(false);
    setSelectedUser(null);
    setVoucherCode('');
  };

  const filteredUsers = users.filter(user => 
    user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    user.email.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-10 font-sans text-gray-800">
      <div className="max-w-6xl mx-auto">
        {/* Header Section */}
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-8 gap-4">
          <div>
            <h1 className="text-3xl font-extrabold text-gray-900 tracking-tight">Quản Lý Người Dùng</h1>
            <p className="text-gray-500 mt-1">Xem và quản lý thông tin thành viên trong hệ thống.</p>
          </div>
          <button 
            onClick={() => setIsModalOpen(true)}
            className="flex items-center justify-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-3 rounded-xl shadow-lg shadow-indigo-200 transition-all active:scale-95"
          >
            <Plus size={20} />
            <span className="font-semibold">Thêm Người Dùng</span>
          </button>
        </div>

        {/* Search Bar */}
        <div className="mb-6 relative">
          <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
            <Search size={18} className="text-gray-400" />
          </div>
          <input 
            type="text"
            placeholder="Tìm kiếm theo tên hoặc email..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-11 pr-4 py-3 bg-white border border-gray-100 rounded-2xl shadow-sm focus:ring-2 focus:ring-indigo-500 outline-none transition-all"
          />
        </div>

        {/* Table Section */}
        <div className="bg-white rounded-3xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="w-full text-left border-collapse">
              <thead>
                <tr className="bg-gray-50/50">
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider">Họ và Tên</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider">Liên hệ</th>
                  <th className="px-6 py-5 text-xs font-bold text-gray-500 uppercase tracking-wider text-center">Thao tác</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {filteredUsers.length > 0 ? filteredUsers.map((user) => (
                  <tr key={user.id} className="hover:bg-gray-50/80 transition-colors">
                    <td className="px-6 py-4">
                      <div className="flex items-center gap-3">
                        <div className="w-10 h-10 bg-indigo-100 text-indigo-600 rounded-full flex items-center justify-center font-bold">
                          {user.name.charAt(0).toUpperCase()}
                        </div>
                        <span className="font-semibold text-gray-900">{user.name}</span>
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      <div className="flex flex-col">
                        <span className="text-sm text-gray-600 flex items-center gap-1"><Mail size={14}/> {user.email}</span>
                        <span className="text-sm text-gray-400 flex items-center gap-1"><Phone size={14}/> {user.phone}</span>
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      <div className="flex justify-center gap-2">
                        {/* NÚT SỬ DỤNG VOUCHER */}
                        <button 
                          onClick={() => openUseVoucherModal(user)}
                          className="flex items-center gap-1 px-3 py-1.5 bg-emerald-50 text-emerald-600 hover:bg-emerald-100 rounded-lg transition-all font-medium text-sm border border-emerald-100"
                        >
                          <TicketCheck size={16} /> Dùng Voucher
                        </button>
                        <button className="p-2 text-gray-400 hover:text-indigo-600 transition-all"><Edit2 size={18} /></button>
                        <button className="p-2 text-gray-400 hover:text-red-600 transition-all"><Trash2 size={18} /></button>
                      </div>
                    </td>
                  </tr>
                )) : (
                  <tr>
                    <td colSpan={3} className="px-6 py-20 text-center text-gray-400 italic">Chưa có người dùng.</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {/* Modal Thêm Người Dùng (Giữ nguyên) */}
      {isModalOpen && (
         <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-gray-900/40 backdrop-blur-sm">
            <div className="bg-white rounded-3xl shadow-2xl w-full max-w-md p-6 animate-in zoom-in duration-200">
               <div className="flex justify-between items-center mb-6">
                  <h2 className="text-xl font-bold">Thêm Thành Viên</h2>
                  <X className="cursor-pointer" onClick={closeModal} />
               </div>
               <form onSubmit={handleSubmit} className="space-y-4">
                  <input type="text" placeholder="Tên" required className="w-full p-3 border rounded-xl" onChange={e => setFormData({...formData, name: e.target.value})} />
                  <input type="email" placeholder="Email" required className="w-full p-3 border rounded-xl" onChange={e => setFormData({...formData, email: e.target.value})} />
                  <input type="text" placeholder="Số điện thoại" className="w-full p-3 border rounded-xl" onChange={e => setFormData({...formData, phone: e.target.value})} />
                  <button type="submit" disabled={isLoading} className="w-full py-3 bg-indigo-600 text-white rounded-xl font-bold">
                     {isLoading ? <Loader2 className="animate-spin mx-auto" /> : "Lưu"}
                  </button>
               </form>
            </div>
         </div>
      )}

      {/* MODAL SỬ DỤNG VOUCHER */}
      {isUseVoucherModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-gray-900/40 backdrop-blur-sm">
          <div className="bg-white rounded-3xl shadow-2xl w-full max-w-sm overflow-hidden animate-in fade-in zoom-in duration-200">
            <div className="p-6 border-b border-gray-100 flex justify-between items-center bg-emerald-50">
              <h2 className="text-xl font-bold text-emerald-900 flex items-center gap-2">
                <TicketCheck size={20}/> Áp dụng Voucher
              </h2>
              <button onClick={closeUseVoucherModal} className="text-emerald-400 hover:text-emerald-600"><X size={24} /></button>
            </div>
            
            <form onSubmit={handleUseVoucher} className="p-6 space-y-4">
              <div className="p-3 bg-gray-50 rounded-xl border border-gray-100">
                <p className="text-xs text-gray-500 uppercase font-bold mb-1">Người sử dụng</p>
                <p className="font-bold text-gray-900">{selectedUser?.name}</p>
              </div>

            <div>
  <label className="block text-sm font-semibold text-gray-700 mb-2">
    Nhập mã khuyến mãi
  </label>
  <input 
    type="text" required autoFocus
    value={voucherCode}
    onChange={(e) => setVoucherCode(e.target.value.toUpperCase())}
    className="w-full px-4 py-3 rounded-xl border-2 border-emerald-100 focus:border-emerald-500 focus:ring-0 outline-none transition-all font-mono text-lg tracking-widest text-center"
    placeholder="VD: GIAM50"
  />
</div>

              <div className="flex gap-3">
                <button 
                  type="button" onClick={closeUseVoucherModal}
                  className="flex-1 py-3 text-gray-500 font-semibold"
                >
                  Hủy
                </button>
                <button 
                  type="submit" disabled={isLoading || !voucherCode}
                  className="flex-1 py-3 bg-emerald-600 text-white font-semibold rounded-xl shadow-lg shadow-emerald-100 flex items-center justify-center gap-2 disabled:bg-emerald-300"
                >
                  {isLoading && <Loader2 size={18} className="animate-spin" />}
                  Xác nhận dùng
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
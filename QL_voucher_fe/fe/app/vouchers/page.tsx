"use client";

import React, { useEffect, useState } from 'react';
import { Plus, Edit2, Trash2, Calendar, Tag, Percent, Box, X, Loader2 } from 'lucide-react';
import { VoucherResponse, VoucherRequest } from '@/modules/vouchers/types/vouchers.type';
import { getAllVouchersUseCase } from '@/modules/vouchers/useCases/getAllVouchersUseCase';
import { createVoucherUseCase } from '@/modules/vouchers/useCases/createVouchersUseCase';
import { updateVoucherUseCase } from '@/modules/vouchers/useCases/updateVouchersUseCase';
import { deleteVoucherUseCase } from '@/modules/vouchers/useCases/deleteVouchersUseCase';

export default function VoucherPage() {
  const [vouchers, setVouchers] = useState<VoucherResponse[]>([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [isLoading, setIsLoading] = useState(false); // Thêm trạng thái chờ

  const [formData, setFormData] = useState<VoucherRequest>({
    code: '',
    discountPercent: 0,
    quantity: 0,
    expiredDate: '',
    status: 'ACTIVE'
  });

  const fetchVouchers = async () => {
    try {
      const data = await getAllVouchersUseCase();
      setVouchers(data || []);
    } catch (error) {
      console.error("Lỗi khi lấy danh sách:", error);
    }
  };

  useEffect(() => {
    fetchVouchers();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true); // Bắt đầu load
    try {
      if (editingId) {
        await updateVoucherUseCase(editingId, formData);
      } else {
        await createVoucherUseCase(formData);
      }
      closeModal();
      await fetchVouchers();
    } catch (error) {
      alert("Có lỗi xảy ra khi lưu dữ liệu!");
    } finally {
      setIsLoading(false); // Tắt load
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setEditingId(null);
    setFormData({ code: '', discountPercent: 0, quantity: 0, expiredDate: '', status: 'ACTIVE' });
  };

  const handleDelete = async (id: number) => {
    if (confirm("Bạn có chắc chắn muốn xóa voucher này?")) {
      try {
        await deleteVoucherUseCase(id);
        await fetchVouchers();
      } catch (error) {
        alert("Xóa thất bại!");
      }
    }
  };

  const openEditModal = (v: VoucherResponse) => {
    setEditingId(v.id);
    setFormData({
      code: v.code,
      discountPercent: v.discountPercent,
      quantity: v.quantity,
      expiredDate: v.expiredDate,
      status: v.status
    });
    setIsModalOpen(true);
  };

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-10 font-sans text-gray-800">
      <div className="max-w-6xl mx-auto">
        {/* Header Section */}
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-8 gap-4">
          <div>
            <h1 className="text-3xl font-extrabold text-gray-900 tracking-tight">Quản Lý Voucher</h1>
            <p className="text-gray-500 mt-1">Quản lý các chương trình khuyến mãi và mã giảm giá của bạn.</p>
          </div>
          <button 
            onClick={() => setIsModalOpen(true)}
            className="flex items-center justify-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-3 rounded-xl shadow-lg shadow-indigo-200 transition-all active:scale-95"
          >
            <Plus size={20} />
            <span className="font-semibold">Thêm Voucher</span>
          </button>
        </div>

        {/* Stats Summary */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-100">
            <p className="text-sm text-gray-500 font-medium">Tổng Voucher</p>
            <p className="text-2xl font-bold text-gray-900">{vouchers.length}</p>
          </div>
        </div>

        {/* Table Section */}
        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="w-full text-left border-collapse">
              <thead>
                <tr className="bg-gray-50/50">
                  <th className="px-6 py-4 text-xs font-bold text-gray-500 uppercase tracking-wider">Mã Code</th>
                  <th className="px-6 py-4 text-xs font-bold text-gray-500 uppercase tracking-wider">Mức giảm</th>
                  <th className="px-6 py-4 text-xs font-bold text-gray-500 uppercase tracking-wider">Số lượng</th>
                  <th className="px-6 py-4 text-xs font-bold text-gray-500 uppercase tracking-wider">Ngày hết hạn</th>
                  <th className="px-6 py-4 text-xs font-bold text-gray-500 uppercase tracking-wider text-center">Thao tác</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {vouchers.length > 0 ? vouchers.map((v) => (
                  <tr key={v.id} className="hover:bg-indigo-50/30 transition-colors group">
                    <td className="px-6 py-4 font-mono font-bold text-indigo-600">{v.code}</td>
                    <td className="px-6 py-4">
                      <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-sm font-medium bg-green-100 text-green-800">
                        {v.discountPercent}% OFF
                      </span>
                    </td>
                    <td className="px-6 py-4 text-gray-600">{v.quantity} chiếc</td>
                    <td className="px-6 py-4 text-gray-600">{v.expiredDate}</td>
                    <td className="px-6 py-4">
                      <div className="flex justify-center gap-2">
                        <button 
                          onClick={() => openEditModal(v)}
                          className="p-2 text-gray-400 hover:text-indigo-600 hover:bg-indigo-50 rounded-lg transition-all"
                        >
                          <Edit2 size={18} />
                        </button>
                        <button 
                          onClick={() => handleDelete(v.id)}
                          className="p-2 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-all"
                        >
                          <Trash2 size={18} />
                        </button>
                      </div>
                    </td>
                  </tr>
                )) : (
                  <tr>
                    <td colSpan={5} className="px-6 py-10 text-center text-gray-400">Chưa có voucher nào. Hãy thêm mới!</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      {/* Modern Modal */}
      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
          <div className="absolute inset-0 bg-gray-900/40 backdrop-blur-sm" onClick={!isLoading ? closeModal : undefined}></div>
          <div className="relative bg-white rounded-3xl shadow-2xl w-full max-w-lg overflow-hidden animate-in fade-in zoom-in duration-200">
            <div className="flex items-center justify-between p-6 border-b border-gray-100">
              <h2 className="text-xl font-bold text-gray-900">{editingId ? 'Chỉnh sửa Voucher' : 'Tạo Voucher mới'}</h2>
              <button onClick={closeModal} disabled={isLoading} className="text-gray-400 hover:text-gray-600 disabled:opacity-50">
                <X size={24} />
              </button>
            </div>
            
            <form onSubmit={handleSubmit} className="p-6">
              <div className="space-y-5">
                <div>
                  <label className="flex items-center gap-2 text-sm font-semibold text-gray-700 mb-2">
                    <Tag size={16} /> Mã Voucher
                  </label>
                  <input 
                    type="text" required value={formData.code}
                    onChange={(e) => setFormData({...formData, code: e.target.value.toUpperCase()})}
                    placeholder="VD: GIAM30"
                    className="w-full px-4 py-3 rounded-xl border border-gray-200 focus:ring-2 focus:ring-indigo-500 focus:border-transparent outline-none transition-all"
                  />
                </div>

                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <label className="flex items-center gap-2 text-sm font-semibold text-gray-700 mb-2">
                      <Percent size={16} /> Giảm (%)
                    </label>
                    <input 
                      type="number" min="1" max="100" required
                      value={formData.discountPercent || ''}
                      onChange={(e) => setFormData({...formData, discountPercent: parseInt(e.target.value) || 0})}
                      className="w-full px-4 py-3 rounded-xl border border-gray-200 focus:ring-2 focus:ring-indigo-500 outline-none"
                    />
                  </div>
                  <div>
                    <label className="flex items-center gap-2 text-sm font-semibold text-gray-700 mb-2">
                      <Box size={16} /> Số lượng
                    </label>
                    <input 
                      type="number" min="0" required
                      value={formData.quantity || ''}
                      onChange={(e) => setFormData({...formData, quantity: parseInt(e.target.value) || 0})}
                      className="w-full px-4 py-3 rounded-xl border border-gray-200 focus:ring-2 focus:ring-indigo-500 outline-none"
                    />
                  </div>
                </div>

                <div>
                  <label className="flex items-center gap-2 text-sm font-semibold text-gray-700 mb-2">
                    <Calendar size={16} /> Ngày hết hạn
                  </label>
                  <input 
                    type="date" required value={formData.expiredDate}
                    onChange={(e) => setFormData({...formData, expiredDate: e.target.value})}
                    className="w-full px-4 py-3 rounded-xl border border-gray-200 focus:ring-2 focus:ring-indigo-500 outline-none"
                  />
                </div>
              </div>

              <div className="mt-8 flex gap-3">
                <button 
                  type="button" onClick={closeModal} disabled={isLoading}
                  className="flex-1 px-4 py-3 border border-gray-200 text-gray-600 font-semibold rounded-xl hover:bg-gray-50 transition-colors disabled:opacity-50"
                >
                  Đóng
                </button>
                <button 
                  type="submit" disabled={isLoading}
                  className="flex-1 px-4 py-3 bg-indigo-600 text-white font-semibold rounded-xl hover:bg-indigo-700 shadow-lg shadow-indigo-100 transition-all flex items-center justify-center gap-2 disabled:bg-indigo-400"
                >
                  {isLoading && <Loader2 size={18} className="animate-spin" />}
                  {editingId ? 'Cập nhật ngay' : 'Tạo Voucher'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
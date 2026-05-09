"use client";

import React from 'react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { Users, Ticket,History, LayoutDashboard, Settings } from 'lucide-react';

export default function Sidebar() {
  const pathname = usePathname();

  const menuItems = [
    { name: 'Bảng điều khiển', href: '/', icon: LayoutDashboard },
    { name: 'Quản lý Voucher', href: '/vouchers', icon: Ticket },
    { name: 'Quản lý User', href: '/users', icon: Users },
    { name: 'Lịch sử sử dụng', href: '/voucher-usages', icon: History },
  ];

  return (
    <div className="fixed left-0 top-0 h-screen w-64 bg-white border-r border-gray-100 flex flex-col p-4 shadow-sm z-40">
      <div className="flex items-center gap-3 px-2 mb-10 mt-2">
        <div className="w-10 h-10 bg-indigo-600 rounded-xl flex items-center justify-center">
          <Ticket className="text-white" size={24} />
        </div>
        <span className="text-xl font-bold text-gray-900 tracking-tight">VoucherApp</span>
      </div>

      <nav className="flex-1 space-y-1">
        {menuItems.map((item) => {
          const isActive = pathname === item.href;
          return (
            <Link
              key={item.href}
              href={item.href}
              className={`flex items-center gap-3 px-4 py-3 rounded-xl font-medium transition-all ${
                isActive 
                  ? 'bg-indigo-50 text-indigo-600' 
                  : 'text-gray-500 hover:bg-gray-50 hover:text-gray-900'
              }`}
            >
              <item.icon size={20} />
              {item.name}
            </Link>
          );
        })}
      </nav>

      <div className="pt-4 border-t border-gray-100">
        <button className="flex items-center gap-3 px-4 py-3 w-full text-gray-500 hover:bg-red-50 hover:text-red-600 rounded-xl transition-all">
          <Settings size={20} />
          <span className="font-medium">Cài đặt</span>
        </button>
      </div>
    </div>
  );
}
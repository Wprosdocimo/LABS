﻿using System;
using System.Threading.Tasks;

namespace ExemplosMongoDB
{
    class Program
    {
        static void Main(string[] args)
        {
            Task T = manipulandoClassesExternas.MainASync(args);
            Console.WriteLine("Precione ENTER");
            Console.ReadLine();
        }
    }
}

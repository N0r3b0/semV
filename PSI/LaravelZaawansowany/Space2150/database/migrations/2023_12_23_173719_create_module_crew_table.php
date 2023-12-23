<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('module_crew', function (Blueprint $table) {
            $table->integer('id')->unsigned();
            $table->foreignId('ship_module_id')->constrained('ship_modules');
            $table->string('nick', 10)->unique()
                ->check('CHAR_LENGTH(nick) >= 3'); // min dlg
            $table->char('gender', 1)
                ->check('gender IN ("M", "F", "N")'); // nie zezwala na inne znaki
            $table->integer('age')->unsigned()
                ->check('age IS NULL OR (age >= 18 AND age <= 85)');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('module_crew');
    }
};

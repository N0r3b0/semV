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
        Schema::create('crew_skills', function (Blueprint $table) {
            $table->integer('id')->unsigned();
            $table->foreignId('module_crew_id')->constrained('module_crew');
            $table->string('name', 15)->unique()
                ->check('CHAR_LENGTH(name) >= 3');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('crew_skills');
    }
};
